package com.picpay.desafio.android.feature.contacts.data.datasource.local

import com.picpay.desafio.android.feature.contacts.data.entities.ContactEntity

interface ContactLocalSource {
    suspend fun getContactsLocal() : List<ContactEntity>
    suspend fun saveContacts(contacts: List<ContactEntity>)
}