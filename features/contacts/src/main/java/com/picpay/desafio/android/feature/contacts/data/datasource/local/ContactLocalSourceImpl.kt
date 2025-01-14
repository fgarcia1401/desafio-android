package com.picpay.desafio.android.feature.contacts.data.datasource.local

import com.picpay.desafio.android.feature.contacts.data.entities.ContactEntity
import kotlinx.coroutines.flow.first
import org.koin.core.annotation.Factory

@Factory
class ContactLocalSourceImpl(
    private val contactDao: ContactDao
) : ContactLocalSource {

    override suspend fun getContactsLocal() = contactDao.getAllContacts().first()

    override suspend fun saveContacts(contacts: List<ContactEntity>) {
        contactDao.deleteAllContacts()
        contactDao.insertAll(contacts)
    }
}
