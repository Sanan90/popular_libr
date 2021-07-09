package com.example.android.pb_rj.final_project.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.pb_rj.databinding.FragmentUser2Binding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import com.example.android.pb_rj.final_project.mvp.model.api.ApiHolder
import com.example.android.pb_rj.final_project.mvp.model.cache.room.RoomGithubRepositoriesCache
import com.example.android.pb_rj.final_project.mvp.model.entity.GithubUser
import com.example.android.pb_rj.final_project.mvp.model.entity.room.db.Database
import com.example.android.pb_rj.final_project.mvp.presenter.UserPresenter
import com.example.android.pb_rj.final_project.mvp.view.UserView
import com.example.android.pb_rj.final_project.ui.App
import com.example.android.pb_rj.final_project.ui.BackButtonListener
import com.example.android.pb_rj.final_project.ui.adapter.ReposotoriesRVAdapter
import com.example.android.pb_rj.final_project.ui.navigation.AndroidScreens
import com.example.android.pb_rj.final_project.mvp.model.repo.RetrofitGithubRepositoriesRepo
import com.example.android.pb_rj.final_project.ui.network.AndroidNetworkStatus

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {

    companion object {
        private const val USER_ARG = "user"

        fun newInstance(user: GithubUser) = UserFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG, user)
            }
        }
    }

    val presenter: UserPresenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(USER_ARG) as GithubUser
        UserPresenter(
            AndroidSchedulers.mainThread(),
            RetrofitGithubRepositoriesRepo(ApiHolder.api, AndroidNetworkStatus(App.instance), RoomGithubRepositoriesCache(Database.getInstance())),
            user
        ).apply {
            App.instance.appComponent.inject(this)
        }
    }

    private var vb: FragmentUser2Binding? = null

    var adapter: ReposotoriesRVAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentUser2Binding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvRepositories?.layoutManager = LinearLayoutManager(context)
        adapter = ReposotoriesRVAdapter(presenter.repositoriesListPresenter)
        vb?.rvRepositories?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }


    override fun backPressed() = presenter.backPressed()
}