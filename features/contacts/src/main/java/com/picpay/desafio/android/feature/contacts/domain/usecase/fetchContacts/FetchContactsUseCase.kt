package com.picpay.desafio.android.feature.contacts.domain.usecase.fetchContacts

import com.picpay.desafio.android.feature.contacts.domain.model.User
import kotlinx.coroutines.flow.Flow

interface FetchContactsUseCase {
    suspend operator fun invoke(): Flow<List<User>>

}