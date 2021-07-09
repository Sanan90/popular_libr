package com.example.android.pb_rj.final_project.di.module

import com.example.android.pb_rj.final_project.ui.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App = app

}