package com.example.android.pb_rj.lesson2.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.pb_rj.databinding.FragmentUserBinding
import com.example.android.pb_rj.databinding.FragmentUsersBinding
import com.example.android.pb_rj.lesson2.mvp.model.entity.GithubUser
import com.example.android.pb_rj.lesson2.mvp.presenter.UserPresenter
import com.example.android.pb_rj.lesson2.mvp.repo.UserRepository
import com.example.android.pb_rj.lesson2.mvp.repo.UserRepositoryFactory
import com.example.android.pb_rj.lesson2.mvp.view.UserView
import com.example.android.pb_rj.lesson2.ui.App
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment(user: GithubUser) : MvpAppCompatFragment(), UserView {

    companion object {
        fun newInstance(user: GithubUser) = UserFragment(user)
    }

    private val presenter by moxyPresenter {
        UserPresenter(user.login, userRepository = UserRepositoryFactory.create(), AndroidSchedulers.mainThread(), App.instance.router)
    }

    private var vb: FragmentUserBinding? = null


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?)
    = FragmentUserBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }


    override fun showChooseUser(user: GithubUser) {
        vb?.userName?.text = user.login
    }

    override fun showError(error: String) {

    }


}