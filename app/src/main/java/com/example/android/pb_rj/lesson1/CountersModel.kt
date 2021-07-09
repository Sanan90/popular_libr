package com.example.android.pb_rj

import io.reactivex.rxjava3.core.Single

class CountersModel(private val countersData: CountersData) {

    fun incrementCounter(counterId: Int): Single<Int> {
        countersData.incrementCounter(counterId)
     return countersData.getCounter(counterId)
    }
}
