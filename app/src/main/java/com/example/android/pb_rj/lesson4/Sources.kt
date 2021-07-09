package com.example.android.pb_rj.lesson3

import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.PublishSubject
import java.lang.RuntimeException
import java.util.concurrent.TimeUnit
import kotlin.random.Random

/**
 * Completable
 * Источник подходит для случаев, когда нам не нужно получать значения,
 * а нас интересует сам факт завершения какой-либо операции,
 * например, запись в файл или базу данных.
 * Для обработки данного источника используется CompletableObserver,
 * аналогичный Observer, но не имеющий onNext.
 * */

/**
 * Single
 * Источник аналогичен Observable, однако может выдать только одно значение.
 * Single идеально подходит для HTTP-запросов,
 * так как всегда ожидается только один ответ от сервера.
 * Получение значения и завершение его работы — одно и то же событие,
 * и вместо onNext и onComplete у его SingleObserver есть только один метод onSuccess,
 * и он терминальный.
 * */

/**
 * Maybe
 * Maybe подходит, для случаев,
 * когда нас устроит как наличие значения, так и его отсутствие,
 * например, при обработке авторизации с возможностью
 * гостевого доступа (в этом случае нас устроит и наличие,
 * и отсутствие авторизованного пользователя).
 * Можно сказать, что он где-то между Completable и Single.
 * Его MaybeObserver помимо onError и onSubscribe имеет методы onSuccess и onComplete,
 * оба из которых терминальные, и, соответственно, взаимоисключающие.
 * При наличии значения вызывается первый, при отсутствии — второй.
 * */

/**
 * Hot Observable
 * Горячий Observable отправляет данные независимо от того,
 * подписан кто-нибудь на него или нет. Если никто его в это время не слушает,
 * данные будут потеряны.
 * Такой подход удобен, например, для оборачивания сокетных соединений,
 * так как данные там идут непрерывно.
 * */


class Sources {

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
        fun randomResultOperation(): Boolean {
            Thread.sleep(Random.nextLong(1000))
            return listOf<Boolean>(true, false, true)[Random.nextInt(2)]
        }


        //  Пример Producer с Completable
        fun completable() = Completable.create { emitter ->
            randomResultOperation().let {
                if (it) {
                    emitter.onComplete()
                } else {
                    emitter.onError(RuntimeException("Error"))
                    return@create
                }
            }
        }

        /**
         * Для всех источников существует свой create, аналогичный тому, что есть у Observable.
         * В данном случае мы используем его,
         * чтобы обернуть симуляцию операции со случайным исходом в Completable,
         * который завершится успехом, если симуляция вернёт true,
         * и выдаст ошибку в противном случае.
         * */


        //  Пример Producer с Single
        fun single() = Single.fromCallable {
            return@fromCallable "some string value"
        }

        /**
         * Для каждого вида источников есть свой аналог fromCallable (для Completable
         * его заменяет fromAction в силу отсутствия возвращаемого значения).
         * Тут мы используем его для создания Single с некоторым строковым значением.
         * Нетрудно представить, например,
         * как в теле fromCallable размещается код сетевого HTTP-запроса и возвращается его результат.
         * */


        //  Пример Producer с Maybe
        fun maybe() = Maybe.create<String> { emitter ->
            randomResultOperation().let {
                if (it) {
                    emitter.onSuccess("Success $it")
                } else {
                    emitter.onComplete()
                    return@create
                }
            }
        }


        //  Пример Producer с hotObservable
        fun hotObservable() = Observable.interval(1, TimeUnit.SECONDS)
            .publish()

//        fun hotObservable() = Observable.interval(1, TimeUnit.SECONDS)
//            .publish()
//            .replay()
        /**
         * Replay
         * Метод replay() используется вместо publish():
         * Отличие в том, что метод кеширует данные, и каждый новый подписчик
         * получит полный набор данных (например, все сообщения чата,
         * пришедшие на клиент до подписки), когда бы он ни подключился.
         * Можно подумать, что таким образом мы получаем обычный холодный Observable,
         * но это не так. Наш источник всё ещё горячий и его работа начнётся
         * только после вызова метода connect().
         * */


//        fun hotObservable() = Observable.interval(1, TimeUnit.SECONDS)
//                .publish()
//                .refCount()
        /**
         * RefCount
         * Этот метод сделает из ConnectableObservable Observable,
         * который начнёт свою работу, как только появится первый подписчик.
         *
         * Обратите внимание, что нет метода connect(),
         * так как класс ConnectableObservable, полученный методом publish(),
         * обёрнут обратно в класс Observable, благодаря использованию метода refCount().
         * При такой конструкции мы получаем горячий Observable,
         * который ведёт себя как холодный.
         * Тем не менее, это будет именно горячий Observable,
         * так как он будет раздавать одни и те же данные всем подписчикам,
         * а не стартовать работу заново для каждого из них.
         * */


        //        fun hotObservable() = Observable.interval(1, TimeUnit.SECONDS)
//                .publish()
//                .cache()
        /**
         * Cache
         * Observable, который будет получен в результате работы этого оператора,
         * будет похож на результат операторов replay() и refCount().
         * Он начинает работу при первом подписчике, хранит все элементы
         * и выдаёт их каждому новому подписчику (даже если он пропустил),
         * но не заканчивает работу при отсутствии подписчиков.
         * */


        fun publishSubject() = PublishSubject.create<String>().apply {
            Observable.timer(2, TimeUnit.SECONDS)
                .subscribe {
                    onNext("Value from subject")
                }
        }

    }

    class Consumer(val producer: Producer) {

        fun exec() {
        }

        fun execCompletable() {
            producer.completable().subscribe(
                { println("onComplete") },
                { println("onError: ${it.message}") }
            )
        }

        fun execSingle() {
            producer.single().map { it + it }
                .subscribe(
                    { s -> println("onSuccess $s") },
                    { println("onError: ${it.message}") }
                )
        }

        fun execMaybe() {
            producer.maybe().map { it + it }
                .subscribe(
                    { s -> println("onSuccess $s") },
                    { println("onError ${it.message}") },
                    { println("onComplete") }
                )
            /**
             * Если случайный результат здесь true — возвращаем onSuccess, в противном случае — onComplete.
             * Нетрудно представить, например,
             * функцию получения текущего пользователя на месте randomResultOperation.
             * */


            fun execHotObservable() {
                val hotObservable = producer.hotObservable()

                hotObservable.subscribe { println(it) }

                hotObservable.connect()

                Thread.sleep(3000)

                hotObservable.subscribe { println("delayed: $it") }
            }
            /**
             * Мы создали interval Observable,
             * затем с помощью оператора publish() создаётся объект ConnectableObservable,
             * метод которого — connect(). Именно этот метод и запускает работу Observable.
             * При этом без разницы, есть подписчики или нет.
             * Если подписчики подписались на горячий Observable одновременно,
             * независимо от момента подписки, они получат одинаковые данные,
             * так как они подключаются к одному и тому же потоку.
             * В этом ключевое отличие от холодного, у которого подписчики
             * получают полный поток данных независимо от момента подключения,
             * так как для каждого создаётся свой поток.
             * Если же, как в примере выше, подписчики подпишутся в разное время,
             * то полученные данные будут отличаться,
             *
             * У горячего Observable есть операторы, которые делают его работу отчасти сходной с работой холодного.
             */

            fun execPublishSubject() {
                val subject = producer.publishSubject()
                subject.subscribe(
                    { println("onNext: $it") },
                    { println("onError: ${it.message}") }
                )
                subject.onNext("from exec")
            }
            /**
             * Создаем PublishSubject с помощью функции create, указав тип значений,
             * которые будут через него ходить. В нашем случае для примера мы выбрали строку.
             * После создания сразу же запускаем таймер,
             * по истечении которого на нашем PublishSubject вызываем onNext.
             * При подписке мы сразу же отправляем в subject другое значение.
             */

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