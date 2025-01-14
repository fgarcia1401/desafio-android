package com.picpay.desafio.android.domain.usecase

import com.picpay.android.common.coroutines.CoroutineTestRule
import com.picpay.desafio.android.commontest.coVerifyOnce
import com.picpay.desafio.android.commontest.relaxedMock
import com.picpay.desafio.android.feature.contacts.data.repository.ContactsRepository
import com.picpay.desafio.android.feature.contacts.domain.usecase.fetchContacts.FetchContactsUseCase
import com.picpay.desafio.android.feature.contacts.domain.usecase.fetchContacts.FetchContactsUseCaseImpl
import com.picpay.desafio.android.util.ContactsHelperTest
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertFailsWith

@OptIn(ExperimentalCoroutinesApi::class)
class FetchContactsUseCaseTest {

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    private var repository: ContactsRepository = relaxedMock()

    private lateinit var useCase: FetchContactsUseCase

    @Before
    fun setUp() {
        clearAllMocks()
        useCase = FetchContactsUseCaseImpl(
            repository = repository
        )
    }

    @Test
    fun `should fetch contacts with success`() = runTest {
        val contacts = ContactsHelperTest.getFakeContacts()

        coEvery { repository.getContacts() } returns contacts

        val result = useCase.invoke().last()

        Assert.assertEquals(contacts, result)
        coVerifyOnce { repository.getContacts() }
    }

    @Test
    fun `should fetch contacts with throw exception`() = runTest {
        coEvery { repository.getContacts() } throws Exception()

        assertFailsWith<Exception> { useCase.invoke().toList() }
        coVerifyOnce { repository.getContacts() }
    }

    @After
    fun tearDown() = unmockkAll()

}
