package com.example.android.pb_rj.lesson2.mvp.repo

import com.example.android.pb_rj.lesson2.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface UserRepository {

    fun fetchUsers() : Single<List<GithubUser>>

    fun fetchUserById(userId : String) : Single<GithubUser>
}