package com.example.android.pb_rj.final_project.mvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}