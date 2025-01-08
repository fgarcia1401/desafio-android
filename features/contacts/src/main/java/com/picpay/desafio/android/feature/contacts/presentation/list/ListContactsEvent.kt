package com.picpay.desafio.android.feature.contacts.presentation.list

import androidx.annotation.StringRes

sealed interface ListContactsEvent {
    data object Initialize : ListContactsEvent
}

sealed interface ListContactsUIEvent {
    data class ShowError(@StringRes val messageResId: Int) : ListContactsUIEvent
}
