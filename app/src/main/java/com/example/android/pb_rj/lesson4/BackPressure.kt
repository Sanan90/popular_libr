package com.example.android.pb_rj.lesson4

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Flowable
 * Flowable аналогичен Observable и отличается от него поддержкой механизма BackPressure.
 * Назначение BackPressure — корректная обработка значений, выдаваемых источником,
 * когда их настолько много, что код не успевает их обработать, например,
 * если мы получаем частые сообщения из сетевого сокета. Такая ситуация может привести к тому,
 * что неуспевающие обработчики съедят всю память и приложение упадёт с OutOfMemoryException.
 * BackPressure позволяет нам распорядиться теми значениями, которые выдает источник в момент,
 * когда обработка предыдущих ещё не завершена.
 * Существует несколько стратегий, применяемых с помощью соответствующих операторов:
 *
 * onBackpressureBuffer() — сохранять значения и обрабатывать по мере возможности.
 * В качестве аргумента может быть передан максимальный размер буфера;
 *
 * onBackpressureDrop() — выбрасывать лишние значения;
 *
 * onBackpressureLatest() — выбрасывать все значения, кроме последнего.
 * */

class BackPressure {
    fun exec() {
        val producer = Producer()
        val consumer = Consumer(producer)
        consumer.consume()
    }

    class Producer {
        fun noBackPressure() = Observable.range(0, 10000000).subscribeOn(Schedulers.io())
        fun backPressure() = Flowable.range(0, 10000000)
            .subscribeOn(Schedulers.computation()).onBackpressureLatest()
    }

    class Consumer(val producer : Producer) {

        fun consume() {
//            consumeNoBackPressure()
            consumeBackPressure()
        }

        private fun consumeNoBackPressure() {
            producer.noBackPressure()
                .observeOn(Schedulers.computation())
                .subscribe({
                    Thread.sleep(1000)
                    println(it.toString())
                }, {
                    println("onError: ${it.message}")
                })
        }

        private fun consumeBackPressure() {
            producer.backPressure()
                .observeOn(Schedulers.computation(), false, 1)
                .subscribe({
                    Thread.sleep(1000)
                    println(it.toString())
                }, {
                    println("onError: ${it.message}")
                })
        }

        /**
         * Тут мы добавили функция backPressure, которая возвращает нам Flowable со стратегией onBackpressureLatest.
         * То есть при перегрузе обработчика будут выбрасываться все значения, кроме последнего
         * Все остальные значения, будут выброшены, так как мы не успевали их обрабатывать.
         * Обратите внимание на аргументы observeOn. Помимо Scheduler появились ещё два.
         * Второй говорит, надо ли воспроизводить последовательности элементов, приводящие к onError,
         * и false тут стандартное значение. Этот аргумент нам не интересен и пояснен просто для информации.
         * Интересен третий аргумент, сообщающий размер буфера observeOn.
         * Дело в том, что у observeOn есть свой буфер значений, размер которого по умолчанию равен 128.
         * В нашем примере этого размера было бы достаточно,
         * чтобы значения выводились по порядку какое-то время (нетрудно догадаться, какое),
         * поэтому в целях демонстрации работы backpressure этот буфер был отключён посредством передачи его размера,
         * равного единице. Поэкспериментируйте самостоятельно с другими стратегиями Back Pressure
         *  ии другими размерами буфера.
         * */
    }
}