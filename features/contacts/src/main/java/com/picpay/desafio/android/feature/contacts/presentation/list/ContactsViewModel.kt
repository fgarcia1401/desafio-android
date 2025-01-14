package com.picpay.desafio.android.feature.contacts.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.android.common.coroutines.safeFlowCollect
import com.picpay.android.common.coroutines.sendInScope
import com.picpay.desafio.android.feature.contacts.R
import com.picpay.desafio.android.feature.contacts.domain.model.User
import com.picpay.desafio.android.feature.contacts.domain.usecase.fetchContacts.FetchContactsUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory

@Factory
class ContactsViewModel(
    private val contactsUseCase: FetchContactsUseCase
) : ViewModel() {

    private var _uiEvent = Channel<ListContactsUIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _state = MutableStateFlow(ListContactsState())
    val state = _state.asStateFlow()

    fun onEvent(event: ListContactsEvent) {
        when (event) {
            ListContactsEvent.Initialize -> fetchContactsUseCase()
        }
    }

    private fun fetchContactsUseCase() = viewModelScope.launch {
        contactsUseCase().safeFlowCollect(
            success = { contacts -> showList(contacts) },
            onError = { showError() },
            onStart = ::loading,
            onCompletion = ::hideLoading
        )
    }

    private fun showList(contacts: List<User>) {
        _state.update {
            it.copy(list = contacts)
        }
    }

    private fun loading() = _state.update {
        it.copy(showLoading = true)
    }

    private fun hideLoading() = _state.update {
        it.copy(showLoading = false)
    }

    private fun showError() =
        _uiEvent.sendInScope(
            this,
            ListContactsUIEvent.ShowError(
                messageResId = R.string.error_list
            )
        )
}
