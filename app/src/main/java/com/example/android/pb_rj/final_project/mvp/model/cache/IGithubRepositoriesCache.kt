package com.example.android.pb_rj.final_project.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import com.example.android.pb_rj.final_project.mvp.model.entity.GithubRepository
import com.example.android.pb_rj.final_project.mvp.model.entity.GithubUser

interface IGithubRepositoriesCache {
    fun getUserRepos(user: GithubUser): Single<List<GithubRepository>>
    fun putUserRepos(user: GithubUser, repositories: List<GithubRepository>): Completable
}