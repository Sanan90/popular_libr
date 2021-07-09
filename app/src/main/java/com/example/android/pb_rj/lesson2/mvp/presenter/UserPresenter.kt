package com.example.android.pb_rj.lesson2.mvp.presenter

import com.example.android.pb_rj.lesson2.mvp.model.entity.GithubUser
import com.example.android.pb_rj.lesson2.mvp.repo.UserRepository
import com.example.android.pb_rj.lesson2.mvp.view.UserView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter





class UserPresenter (
    private val userId : String,
    private val userRepository : UserRepository,
    private val schedulers : Scheduler,
    private val router: Router) :
        MvpPresenter<UserView>() {

    private val disposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        disposable +=
            userRepository
                .fetchUserById(userId)
                .observeOn(schedulers)
                .subscribeOn(schedulers)
                .subscribe(
                    ::onFetchUserByIdSuccess,
                    ::onFetchUserByIdError
                )
    }

    private fun onFetchUserByIdSuccess(user : GithubUser) {
        viewState.showChooseUser(user)
    }

    private fun onFetchUserByIdError(error : Throwable) {
        viewState.showError(error.message!!)
    }

}