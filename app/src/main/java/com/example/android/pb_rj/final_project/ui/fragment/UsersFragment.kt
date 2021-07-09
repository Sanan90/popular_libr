package com.example.android.pb_rj.final_project.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.pb_rj.databinding.FragmentUsers2Binding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import com.example.android.pb_rj.final_project.mvp.model.api.ApiHolder
import com.example.android.pb_rj.final_project.mvp.model.cache.room.RoomGithubUsersCache
import com.example.android.pb_rj.final_project.mvp.model.cache.room.RoomImageCache
import com.example.android.pb_rj.final_project.mvp.model.entity.room.db.Database
import com.example.android.pb_rj.final_project.mvp.model.repo.RetrofitGithubUsersRepo
import com.example.android.pb_rj.final_project.mvp.presenter.UsersPresenter
import com.example.android.pb_rj.final_project.mvp.view.UsersView
import com.example.android.pb_rj.final_project.ui.App
import com.example.android.pb_rj.final_project.ui.BackButtonListener
import com.example.android.pb_rj.final_project.ui.adapter.UsersRVAdapter
import com.example.android.pb_rj.final_project.ui.image.GlideImageLoader
import com.example.android.pb_rj.final_project.ui.network.AndroidNetworkStatus

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    companion object {
        fun newInstance() = UsersFragment()
    }

    val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            AndroidSchedulers.mainThread()
        ).apply {
            App.instance.appComponent.inject(this)
        }
    }

    var adapter: UsersRVAdapter? = null
    private var vb: FragmentUsers2Binding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentUsers2Binding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(
            presenter.usersListPresenter,
            GlideImageLoader(
                RoomImageCache(Database.getInstance(), App.instance.cacheDir),
                AndroidNetworkStatus(requireContext())
            )
        )
        vb?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}