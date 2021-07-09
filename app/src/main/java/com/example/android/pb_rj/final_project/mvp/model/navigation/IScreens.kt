package com.example.android.pb_rj.final_project.mvp.model.navigation

import com.github.terrakok.cicerone.Screen
import com.example.android.pb_rj.final_project.mvp.model.entity.GithubRepository
import com.example.android.pb_rj.final_project.mvp.model.entity.GithubUser

interface IScreens {
    fun users(): Screen
    fun user(githubUser: GithubUser): Screen
    fun repository(githubRepository: GithubRepository): Screen
}