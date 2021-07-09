package com.example.android.pb_rj.final_project.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubRepository(
    @Expose val id: String,
    @Expose val name: String? = null,
    @Expose val forksCount: Int? = null
) : Parcelable

