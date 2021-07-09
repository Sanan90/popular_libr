package com.example.android.pb_rj.final_project.mvp.model.cache.room

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import com.example.android.pb_rj.final_project.mvp.model.entity.GithubRepository
import com.example.android.pb_rj.final_project.mvp.model.entity.GithubUser
import com.example.android.pb_rj.final_project.mvp.model.cache.IGithubRepositoriesCache
import com.example.android.pb_rj.final_project.mvp.model.entity.room.RoomGithubRepository
import com.example.android.pb_rj.final_project.mvp.model.entity.room.db.Database

class RoomGithubRepositoriesCache(val db: Database) : IGithubRepositoriesCache {

    override fun getUserRepos(user: GithubUser) = Single.fromCallable {
        val roomUser =
            db.userDao.findByLogin(user.login) ?: throw RuntimeException("No such user in cache")
        return@fromCallable db.repositoryDao.findForUser(roomUser.id)
            .map { GithubRepository(it.id, it.name, it.forksCount) }
    }.subscribeOn(Schedulers.io())

    override fun putUserRepos(user: GithubUser, repositories: List<GithubRepository>) =
        Completable.fromAction {
            val roomUser = db.userDao.findByLogin(user.login)
                ?: throw RuntimeException("No such user in cache")
            val roomRepos = repositories.map {
                RoomGithubRepository(it.id, it.name ?: "", it.forksCount ?: 0, roomUser.id)
            }
            db.repositoryDao.insert(roomRepos)

        }.subscribeOn(Schedulers.io())

}