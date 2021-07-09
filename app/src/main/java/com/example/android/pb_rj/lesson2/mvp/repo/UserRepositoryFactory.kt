package com.example.android.pb_rj.lesson2.mvp.repo

import com.example.android.pb_rj.lesson2.data.user.MockedUserRepository
import com.example.android.pb_rj.lesson2.mvp.model.GithubUsersRepo
import com.example.android.pb_rj.lesson2.mvp.model.MockedUsers
import com.example.android.pb_rj.lesson2.mvp.model.entity.GithubUser

object UserRepositoryFactory {
    fun create() : UserRepository = MockedUserRepository(MockedUsers.users)

}