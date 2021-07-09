package com.example.android.pb_rj.lesson2.ui.activity

import android.os.Bundle
import com.example.android.pb_rj.R
import com.example.android.pb_rj.databinding.ActivityMainBinding
import com.example.android.pb_rj.lesson2.mvp.view.MainView
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import com.example.android.pb_rj.lesson2.mvp.presenter.MainPresenter
import com.example.android.pb_rj.lesson2.ui.App
import com.example.android.pb_rj.lesson2.ui.BackButtonListener
import com.example.android.pb_rj.lesson2.ui.adapter.UsersRVAdapter
import com.example.android.pb_rj.lesson2.ui.navigation.AndroidScreens

class MainActivity : MvpAppCompatActivity(), MainView {

    val navigator = AppNavigator(this, R.id.container)

    private var vb: ActivityMainBinding? = null
    private val presenter by moxyPresenter {
        MainPresenter(App.instance.router, AndroidScreens())
    }

    private var adapter: UsersRVAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
         App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if(it is BackButtonListener && it.backPressed()){
                return
            }
        }
        presenter.backPressed()
    }
}



//class MainActivity : MvpAppCompatActivity(activity_main) {
//
//    private val navigator = AppNavigator(this, R.id.container)
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        savedInstanceState ?: router.newRootScreen(UsersScreen)
//    }
//
//    override fun onResumeFragments() {
//        super.onResumeFragments()
//        navigatorHolder.setNavigator(navigator)
//    }
//
//    override fun onPause() {
//        navigatorHolder.removeNavigator()
//        super.onPause()
//    }