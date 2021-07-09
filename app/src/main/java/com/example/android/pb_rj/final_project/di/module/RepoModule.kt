package com.example.android.pb_rj.final_project.di.module

import dagger.Module
import dagger.Provides
import com.example.android.pb_rj.final_project.mvp.model.api.IDataSource
import com.example.android.pb_rj.final_project.mvp.model.cache.IGithubUsersCache
import com.example.android.pb_rj.final_project.mvp.model.network.INetworkStatus
import com.example.android.pb_rj.final_project.mvp.model.repo.IGithubUsersRepo
import com.example.android.pb_rj.final_project.mvp.model.repo.RetrofitGithubUsersRepo
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IGithubUsersCache
    ): IGithubUsersRepo = RetrofitGithubUsersRepo(api, networkStatus, cache)

}