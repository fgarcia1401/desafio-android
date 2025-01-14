package com.picpay.desafio.android.feature.contacts.data.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserResponse(
    @SerializedName("img") val img: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("id") val id: Int,
    @SerializedName("username") val username: String? = null
) : Parcelable
