package com.example.android.pb_rj.final_project.ui.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.example.android.pb_rj.final_project.mvp.model.entity.GithubRepository
import com.example.android.pb_rj.final_project.mvp.model.entity.GithubUser
import com.example.android.pb_rj.final_project.mvp.model.navigation.IScreens
import com.example.android.pb_rj.final_project.ui.fragment.RepositoryFragment
import com.example.android.pb_rj.final_project.ui.fragment.UserFragment
import com.example.android.pb_rj.final_project.ui.fragment.UsersFragment

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun user(githubUser: GithubUser) = FragmentScreen { UserFragment.newInstance(githubUser) }
    override fun repository(githubRepository: GithubRepository) = FragmentScreen { RepositoryFragment.newInstance(githubRepository) }
}