package com.example.android.pb_rj.final_project.mvp.model.repo

import io.reactivex.rxjava3.schedulers.Schedulers
import com.example.android.pb_rj.final_project.mvp.model.api.IDataSource
import com.example.android.pb_rj.final_project.mvp.model.cache.IGithubUsersCache
import com.example.android.pb_rj.final_project.mvp.model.network.INetworkStatus

class RetrofitGithubUsersRepo(val api: IDataSource, val networkStatus: INetworkStatus, val cache: IGithubUsersCache) :
    IGithubUsersRepo {
    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers()
                .flatMap { users ->
                    cache.putUsers(users).toSingleDefault(users)
                }
        } else {
            cache.getUsers()
        }
    }.subscribeOn(Schedulers.io())
}