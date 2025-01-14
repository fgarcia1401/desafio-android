package com.picpay.desafio.android.presentation

import com.picpay.android.common.coroutines.CoroutineTestRule
import com.picpay.desafio.android.commontest.coVerifyOnce
import com.picpay.desafio.android.commontest.flowException
import com.picpay.desafio.android.commontest.hasNotEvent
import com.picpay.desafio.android.commontest.observe
import com.picpay.desafio.android.commontest.relaxedMock
import com.picpay.desafio.android.commontest.withFirstEvent
import com.picpay.desafio.android.commontest.withLastEvent
import com.picpay.desafio.android.feature.contacts.R
import com.picpay.desafio.android.feature.contacts.domain.usecase.fetchContacts.FetchContactsUseCase
import com.picpay.desafio.android.feature.contacts.presentation.list.ContactsViewModel
import com.picpay.desafio.android.feature.contacts.presentation.list.ListContactsEvent.Initialize
import com.picpay.desafio.android.feature.contacts.presentation.list.ListContactsState
import com.picpay.desafio.android.feature.contacts.presentation.list.ListContactsUIEvent.ShowError
import com.picpay.desafio.android.util.ContactsHelperTest
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ContactsViewModelTest {

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    private var useCase: FetchContactsUseCase = relaxedMock()

    private val viewModel = ContactsViewModel(
        contactsUseCase = useCase,
    )

    @Before
    fun setUp() {
        clearAllMocks()
    }

    @Test
    fun `when init fetch contacts and show contact, hide loading and not show error`() = runTest {
        val contacts = ContactsHelperTest.getFakeContacts()

        coEvery { useCase.invoke() } returns flow { emit(contacts) }

        observe(viewModel.state) {
            viewModel.onEvent(Initialize)
        }.withFirstEvent<ListContactsState> {
            assertEquals(listOf(), it.list)
        }.withLastEvent<ListContactsState> {
            assertEquals(contacts, it.list)
            assertEquals(false, it.showLoading)
        }

        coVerifyOnce { useCase.invoke() }

        observe(viewModel.uiEvent) { viewModel.onEvent(Initialize) }.hasNotEvent<ShowError>()
    }

    @Test
    fun `when exception show default error and not show contacts`() = runTest {
        coEvery { useCase.invoke() } returns flowException()

        observe(viewModel.uiEvent) {
            viewModel.onEvent(Initialize)
        }.withLastEvent<ShowError> {
            assertEquals(R.string.error_list, it.messageResId)
        }

        assertEquals(listOf(), viewModel.state.value.list)
        assertEquals(false, viewModel.state.value.showLoading)

        coVerifyOnce { useCase.invoke() }
    }

    @After
    fun tearDown() = unmockkAll()
}
