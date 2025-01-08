package com.picpay.desafio.android.feature.contacts.data.api

import com.picpay.desafio.android.feature.contacts.data.entities.UserResponse
import retrofit2.http.GET

interface PicPayService {
    @GET(USERS)
    suspend fun getUsers(): List<UserResponse>

    private companion object {
        const val USERS = "users"
    }
}