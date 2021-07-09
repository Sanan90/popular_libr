package com.example.android.pb_rj.final_project.mvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import com.example.android.pb_rj.final_project.mvp.model.entity.GithubRepository
import com.example.android.pb_rj.final_project.mvp.view.RepositoryView
import javax.inject.Inject

class RepositoryPresenter(val githubRepository: GithubRepository) : MvpPresenter<RepositoryView>() {

    @Inject lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.setId(githubRepository.id ?: "")
        viewState.setTitle(githubRepository.name ?: "")
        viewState.setForksCount(githubRepository.forksCount?.toString() ?: "")
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
