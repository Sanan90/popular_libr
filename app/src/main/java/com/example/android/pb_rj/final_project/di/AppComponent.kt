package com.example.android.pb_rj.final_project.di

import dagger.Component
import com.example.android.pb_rj.final_project.di.module.*
import com.example.android.pb_rj.final_project.mvp.presenter.MainPresenter
import com.example.android.pb_rj.final_project.mvp.presenter.RepositoryPresenter
import com.example.android.pb_rj.final_project.mvp.presenter.UserPresenter
import com.example.android.pb_rj.final_project.mvp.presenter.UsersPresenter
import com.example.android.pb_rj.final_project.ui.activity.MainActivity
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        ApiModule::class,
        RepoModule::class,
        CacheModule::class
    ]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(userPresenter: UserPresenter)
    fun inject(repositoryPresenter: RepositoryPresenter)
}