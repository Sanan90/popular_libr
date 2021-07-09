package com.example.android.pb_rj.final_project.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import com.example.android.pb_rj.final_project.mvp.model.api.IDataSource
import com.example.android.pb_rj.final_project.mvp.model.cache.IGithubRepositoriesCache
import com.example.android.pb_rj.final_project.mvp.model.entity.GithubRepository
import com.example.android.pb_rj.final_project.mvp.model.entity.GithubUser
import com.example.android.pb_rj.final_project.mvp.model.network.INetworkStatus
import java.lang.RuntimeException

class RetrofitGithubRepositoriesRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val cache: IGithubRepositoriesCache
) :
    IGithubRepositoriesRepo {
    override fun getRepositories(user: GithubUser) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl?.let { url ->
                    api.getRepositories(url)
                        .flatMap { repositories ->
                            cache.putUserRepos(user, repositories).toSingleDefault(repositories)
                        }
                } ?: Single.error<List<GithubRepository>>(RuntimeException("User has no repos url"))
                    .subscribeOn(Schedulers.io())
            } else {
                cache.getUserRepos(user)
            }
        }.subscribeOn(Schedulers.io())
}

interface A

class AImpl : A
class AImpl2 : A


class B(val a: A) {

    fun doSomethingWithInstanceOfA() {
        a.toString()
    }
}