package com.example.android.pb_rj.lesson3

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class Creation {

    fun exec() {
        Consumer(Producer()).exec()
    }

    /**
     * Теперь в Producer мы будем создавать Observable разными способами,
     * а в Consumer будем на них подписываться.
     * При запуске функции exec внешнего класса Creation,
     * например, из onCreate нашего главного активити,
     * всё это будет запускаться и выводить результат.
     * */
    class Producer {
        fun observable() : Observable<String> {
            return Observable.just("1", "2")
        }
    }

    class Consumer(val producer : Producer) {

        val stringObserver = object : Observer<String> {
            var disposable : Disposable? = null

            override fun onComplete() {
                println("onComplete")
            }

            override fun onSubscribe(d: Disposable?) {
                disposable = d
                println("onSubscribe")
            }

            override fun onNext(s: String?) {
                println("onNext $s")
            }

            override fun onError(e: Throwable?) {
                println("onError ${e?.message}")
            }
        }

        //Тут мы с помощью Observer подписываемся на Observable,
        // возвращаемый функцией observale() нашего Producer.
        fun execObservable() {
            producer.observable().subscribe(stringObserver)
        }

        fun exec() {
            execObservable()
        }
    }
}

/**
 *  Теперь, когда мы это запустим с помощью функции exec(),
 *  Observer подпишется на Observable,
 *  и в этот момент сработает событие onSubscribe(),
 *  после чего пойдёт передача данных.
 *  Пока будет идти передача данных из последовательности,
 *  будет срабатывать событие onNext() Observer.
 *  Причём данные из цепочки будут передаваться
 *  как параметр s функции onNext() по очереди.
 *  В конце сработает событие onCompleted().
 *  Вообще при передаче данных
 *  может сработать один из двух терминаторов:
 *  либо onCompleted(), либо onError().
 *  После их срабатывания передача данных прекращается.
 */