package com.example.android.pb_rj.lesson2.ui.navigation

import com.example.android.pb_rj.lesson2.mvp.model.entity.GithubUser
import com.example.android.pb_rj.lesson2.mvp.navigation.IScreens
import com.example.android.pb_rj.lesson2.ui.fragment.UserFragment
import com.example.android.pb_rj.lesson2.ui.fragment.UsersFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen {
        UsersFragment.newInstance()
    }

    override fun user(user: GithubUser) = FragmentScreen {
        UserFragment.newInstance(user)
    }
}