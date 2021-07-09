package com.example.android.pb_rj.lesson2

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers


class SchedulerImpl : com.example.android.pb_rj.lesson2.Scheduler {

    override fun background(): Scheduler = io.reactivex.rxjava3.schedulers.Schedulers.newThread()

    override fun main(): Scheduler = AndroidSchedulers.mainThread()

}