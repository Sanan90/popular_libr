package com.example.android.pb_rj.final_project.ui

import android.app.Application
import com.example.android.pb_rj.final_project.di.DaggerAppComponent
import com.example.android.pb_rj.final_project.di.AppComponent
import com.example.android.pb_rj.final_project.di.module.AppModule
import com.example.android.pb_rj.final_project.mvp.model.entity.room.db.Database

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        Database.create(this)

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}
