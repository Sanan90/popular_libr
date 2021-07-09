package com.example.android.pb_rj.lesson2.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleTagStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.SingleState


@AddToEndSingle
interface UsersView : MvpView {

    /**
     * Так как всё, что появится на экране — просто список,
     * интерфейс включает всего два метода:
     * init() — для первичной инициализации списка,
     * который мы будем вызывать при присоединении View к Presenter;
     * updateList() — для обновления содержимого списка.
     */


        fun init()
        fun updateList()
    }



//interface UsersView : MvpView {
//
//    @SingleState
//    fun showUsers(users : List<User>)
//}