package com.picpay.desafio.android.feature.contacts.data.repository

import com.picpay.desafio.android.feature.contacts.domain.model.User

interface ContactsRepository {
    suspend fun getContacts(): List<User>
}