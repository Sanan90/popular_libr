package com.example.android.pb_rj.lesson2.mvp.presenter

import com.example.android.pb_rj.lesson2.mvp.model.entity.GithubUser
import com.example.android.pb_rj.lesson2.mvp.view.UserView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserPresenter (val user : GithubUser, val router: Router) :
        MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showChooseUser(user.login)
    }
}