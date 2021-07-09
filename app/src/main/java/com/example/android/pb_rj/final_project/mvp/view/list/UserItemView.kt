package com.example.android.pb_rj.final_project.mvp.view.list

interface UserItemView: IItemView {
    fun setLogin(text: String)
    fun loadAvatar(url: String)
}