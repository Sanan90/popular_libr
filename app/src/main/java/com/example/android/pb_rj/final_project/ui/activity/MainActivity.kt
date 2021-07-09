package com.example.android.pb_rj.final_project.ui.activity

import android.os.Bundle
import com.example.android.pb_rj.R
import com.example.android.pb_rj.databinding.ActivityMain2Binding
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import com.example.android.pb_rj.final_project.mvp.presenter.MainPresenter
import com.example.android.pb_rj.final_project.mvp.view.MainView
import com.example.android.pb_rj.final_project.ui.App
import com.example.android.pb_rj.final_project.ui.BackButtonListener
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    @Inject lateinit var navigatorHolder: NavigatorHolder
    val navigator = AppNavigator(this, R.id.container)

    private val presenter by moxyPresenter {
        MainPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }
    private var vb: ActivityMain2Binding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.inject(this)
        vb = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(vb?.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if(it is BackButtonListener && it.backPressed()){
                return
            }
        }
        presenter.backClicked()
    }
}
