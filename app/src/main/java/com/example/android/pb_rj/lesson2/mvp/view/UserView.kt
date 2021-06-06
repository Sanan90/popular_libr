package com.example.android.pb_rj.lesson2.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface UserView : MvpView {
    fun showChooseUser(login : String)
}