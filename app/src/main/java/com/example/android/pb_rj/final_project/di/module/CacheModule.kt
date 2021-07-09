package com.example.android.pb_rj.final_project.di.module

import androidx.room.Room
import dagger.Module
import dagger.Provides
import com.example.android.pb_rj.final_project.mvp.model.cache.IGithubUsersCache
import com.example.android.pb_rj.final_project.mvp.model.cache.room.RoomGithubUsersCache
import com.example.android.pb_rj.final_project.mvp.model.entity.room.db.Database
import com.example.android.pb_rj.final_project.ui.App
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App) = Room.databaseBuilder(app, Database::class.java, Database.DB_NAME)
        .build()

    @Singleton
    @Provides
    fun usersCahche(db: Database): IGithubUsersCache = RoomGithubUsersCache(db)

}