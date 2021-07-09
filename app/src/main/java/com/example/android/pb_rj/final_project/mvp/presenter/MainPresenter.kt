package com.example.android.pb_rj.final_project.mvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import com.example.android.pb_rj.final_project.mvp.model.navigation.IScreens
import com.example.android.pb_rj.final_project.mvp.view.MainView
import javax.inject.Inject

class MainPresenter() : MvpPresenter<MainView>() {

    @Inject lateinit var router: Router
    @Inject lateinit var screens: IScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }
}
