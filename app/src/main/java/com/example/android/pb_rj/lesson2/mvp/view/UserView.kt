package com.example.android.pb_rj.lesson2.mvp.view

import com.example.android.pb_rj.lesson2.mvp.model.entity.GithubUser
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface   UserView : MvpView {
    fun showChooseUser(user : GithubUser)
    fun showError(error : String)
}