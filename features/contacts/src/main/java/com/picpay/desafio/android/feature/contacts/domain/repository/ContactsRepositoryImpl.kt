package com.picpay.desafio.android.feature.contacts.domain.repository

import com.picpay.android.common.coroutines.CustomDispatchers
import com.picpay.desafio.android.feature.contacts.data.datasource.remote.ContactsRemote
import com.picpay.desafio.android.feature.contacts.data.repository.ContactsRepository
import com.picpay.desafio.android.feature.contacts.domain.mappers.ContactsMapper
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory

@Factory
class ContactsRepositoryImpl(
    private val remote: ContactsRemote,
    private val dispatchers: CustomDispatchers,
) : ContactsRepository {
    override suspend fun getContacts() = withContext(dispatchers.io()) {
        runCatching {
            remote.getContacts()
        }.map { ContactsMapper.transform(it) }.getOrThrow()
    }

}