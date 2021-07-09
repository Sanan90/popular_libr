package com.example.android.pb_rj.final_project.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Versioned(val version: Int)