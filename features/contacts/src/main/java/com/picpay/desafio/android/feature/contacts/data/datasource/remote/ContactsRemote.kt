package com.picpay.desafio.android.feature.contacts.data.datasource.remote

import com.picpay.desafio.android.feature.contacts.data.entities.UserResponse

interface ContactsRemote {
    suspend fun getContacts(): List<UserResponse>
}
