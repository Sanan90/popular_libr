package com.example.android.pb_rj.final_project.di.module

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import com.example.android.pb_rj.final_project.mvp.model.api.IDataSource
import com.example.android.pb_rj.final_project.mvp.model.network.INetworkStatus
import com.example.android.pb_rj.final_project.ui.App
import com.example.android.pb_rj.final_project.ui.network.AndroidNetworkStatus
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Named("baseUrl")
    @Provides
    fun baseUrl(): String = "https://api.github.com"

    @Named("other")
    @Provides
    fun otherString(): String = "124124"

    @Provides
    @Singleton
    fun api(@Named("baseUrl") baseUrl: String, gson: Gson): IDataSource = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(IDataSource::class.java)

    
    @Provides
    @Singleton
    fun gson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .excludeFieldsWithoutExposeAnnotation()
        .create()


    @Provides
    @Singleton
    fun networkStatus(app: App): INetworkStatus = AndroidNetworkStatus(app)
}