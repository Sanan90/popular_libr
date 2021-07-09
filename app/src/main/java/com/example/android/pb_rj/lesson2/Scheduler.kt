package com.example.android.pb_rj.lesson2

import io.reactivex.rxjava3.core.Scheduler

interface Scheduler {

    fun background() : Scheduler

    fun main() : Scheduler
}