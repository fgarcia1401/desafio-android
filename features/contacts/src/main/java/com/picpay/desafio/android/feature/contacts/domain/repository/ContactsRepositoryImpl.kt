package com.picpay.desafio.android.feature.contacts.domain.repository

import com.picpay.android.common.coroutines.CustomDispatchers
import com.picpay.desafio.android.feature.contacts.data.datasource.local.ContactLocalSource
import com.picpay.desafio.android.feature.contacts.data.datasource.remote.ContactsRemote
import com.picpay.desafio.android.feature.contacts.data.repository.ContactsRepository
import com.picpay.desafio.android.feature.contacts.domain.mappers.ContactsEntityMapper
import com.picpay.desafio.android.feature.contacts.domain.mappers.ContactsResponseMapper
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory

@Factory
class ContactsRepositoryImpl(
    private val remote: ContactsRemote,
    private val local: ContactLocalSource,
    private val dispatchers: CustomDispatchers,
) : ContactsRepository {

    override suspend fun getContacts() = withContext(dispatchers.io()) {
        runCatching {
            refreshContactsFromRemote()
            getLocalContacts()
        }.getOrElse { throwable -> treatException(throwable) }
    }

    private suspend fun refreshContactsFromRemote() {
        val userResponses = remote.getContacts()
        local.saveContacts(contacts = ContactsResponseMapper.transform(userResponses))
    }

    private suspend fun getLocalContacts() =
        local.getContactsLocal().let { contacts ->
            contacts.map { ContactsEntityMapper.transform(it) }
        }

    private suspend fun treatException(throwable: Throwable) =
        local.getContactsLocal().takeIf { it.isNotEmpty() }?.let { contacts ->
            contacts.map { ContactsEntityMapper.transform(it) }
        } ?: run { throw throwable }

}
