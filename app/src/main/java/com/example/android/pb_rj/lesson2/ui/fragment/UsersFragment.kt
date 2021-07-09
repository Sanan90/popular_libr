package com.example.android.pb_rj.lesson2.ui.fragment

import moxy.ktx.moxyPresenter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.pb_rj.databinding.FragmentUsersBinding
import com.example.android.pb_rj.lesson2.SchedulerFactory
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import com.example.android.pb_rj.lesson2.mvp.model.GithubUsersRepo
import com.example.android.pb_rj.lesson2.mvp.presenter.UsersPresenter
import com.example.android.pb_rj.lesson2.mvp.repo.UserRepositoryFactory
import com.example.android.pb_rj.lesson2.mvp.view.UsersView
import com.example.android.pb_rj.lesson2.ui.App
import com.example.android.pb_rj.lesson2.ui.BackButtonListener
import com.example.android.pb_rj.lesson2.ui.adapter.UsersRVAdapter
import com.example.android.pb_rj.lesson2.ui.navigation.AndroidScreens


class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter by moxyPresenter {
        UsersPresenter(userRepository = UserRepositoryFactory.create(), App.instance.router, SchedulerFactory.create())
    }

    private var vb: FragmentUsersBinding? = null
    private var adapter: UsersRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUsersBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(requireContext())
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        vb?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPress()

}
//
///**
// * Теперь всё на своих местах.
// * В функции init() инициализируем адаптер и передаём туда Presenter списка,
// * а в функции updateList() командуем адаптеру обновить список.
// * */




//class UsersFragment : MvpAppCompatFragment(fragment_users), UsersView, UsersAdapter.Delegate {
//
//    companion object {
//        fun newInstance() : Fragment = UsersFragment().arguments()
//    }
//
//    val presenter by moxyPresenter {
//        UsersPresenter(userRepository = UserRepositoryFactory.create(), router = router)
//    }
//
//    val usersAdapter = UsersAdapter(delegate = this)
//
//    private var vb : FragmentUsersBinding? = null
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ) : View = FragmentUsersBinding.inflate(inflater, container, false)
//        .apply {
//        vb = this
//            vb?.rvUsers?.adapter = usersAdapter
//    }.root
//
//    override fun showUsers(users : List<User>) =
//        usersAdapter.submitList(users)
//
//    override fun onUserPicked(user : User) =
//        presenter.displayUser(user)
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        vb = null
//    }
//}