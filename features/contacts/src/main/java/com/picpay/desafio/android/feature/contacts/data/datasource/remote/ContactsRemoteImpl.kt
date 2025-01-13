package com.picpay.desafio.android.feature.contacts.data.datasource.remote

import com.picpay.desafio.android.feature.contacts.data.api.PicPayService
import org.koin.core.annotation.Single

@Single
class ContactsRemoteImpl(
    private val service: PicPayService,
) : ContactsRemote {
    override suspend fun getContacts() = service.getUsers()
}