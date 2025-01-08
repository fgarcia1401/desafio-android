package com.picpay.desafio.android.feature.contacts.presentation.list

import com.picpay.desafio.android.feature.contacts.domain.model.User

data class ListContactsState(
    val list: List<User> = listOf(),
    val showLoading: Boolean = false
)
