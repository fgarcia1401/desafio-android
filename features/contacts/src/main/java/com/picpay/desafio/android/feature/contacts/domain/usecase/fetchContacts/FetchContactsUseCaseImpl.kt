package com.picpay.desafio.android.feature.contacts.domain.usecase.fetchContacts

import com.picpay.desafio.android.feature.contacts.data.repository.ContactsRepository
import kotlinx.coroutines.flow.flow
import org.koin.core.annotation.Factory

@Factory
class FetchContactsUseCaseImpl(
    private val repository: ContactsRepository
) : FetchContactsUseCase {
    override suspend fun invoke() = flow { emit(repository.getContacts()) }
}