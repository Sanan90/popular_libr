package com.example.android.pb_rj.final_project.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import com.example.android.pb_rj.final_project.mvp.model.entity.GithubUser

interface IGithubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
}