package com.example.android.pb_rj.lesson2.data.user

import com.example.android.pb_rj.lesson2.mvp.model.entity.GithubUser
import com.example.android.pb_rj.lesson2.mvp.repo.UserRepository
import io.reactivex.rxjava3.core.Single

class MockedUserRepository(private val users: List<GithubUser>) : UserRepository {

    override fun fetchUsers(): Single<List<GithubUser>> =
        Single.just(users)

    override fun fetchUserById(userId: String): Single<GithubUser> =
        Single.fromCallable {
            users.firstOrNull { user -> userId == user.login }
        }


}