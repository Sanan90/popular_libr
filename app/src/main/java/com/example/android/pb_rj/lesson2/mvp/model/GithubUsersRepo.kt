package com.example.android.pb_rj.lesson2.mvp.model

import com.example.android.pb_rj.lesson2.mvp.model.entity.GithubUser
import com.example.android.pb_rj.lesson2.mvp.repo.IGithubUsersRepo
import io.reactivex.rxjava3.core.Single

class GithubUsersRepo : IGithubUsersRepo{
    private val users = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5")
    )


    override fun getUsers(): Single<List<GithubUser>> {
        return Single.just(users)
    }

    override fun getUser(userId : Int): Single<GithubUser> =
        Single.just(users[userId])
}