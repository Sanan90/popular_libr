package com.example.android.pb_rj.final_project.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import com.example.android.pb_rj.final_project.mvp.model.entity.GithubUser

interface IGithubUsersCache {
    fun getUsers(): Single<List<GithubUser>>
    fun putUsers(users: List<GithubUser>) : Completable
}