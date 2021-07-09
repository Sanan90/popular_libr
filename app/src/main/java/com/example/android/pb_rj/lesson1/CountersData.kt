package com.example.android.pb_rj

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class CountersData(private val counters: MutableList<Int> = mutableListOf(0, 0, 0)) {

    fun getCounter(counterId: Int): Single<Int> =
        Single.just(++counters[counterId])
    //  Single.fromCallable { ++counters[counterId] }

    fun incrementCounter(counterId: Int) : Completable =
        Completable.fromCallable {
            ++counters[counterId]
        }

}