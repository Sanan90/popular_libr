package com.example.android.pb_rj.lesson2.mvp.navigation

import com.example.android.pb_rj.lesson2.mvp.model.entity.GithubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users() : Screen

    fun user(user: GithubUser) : Screen

}