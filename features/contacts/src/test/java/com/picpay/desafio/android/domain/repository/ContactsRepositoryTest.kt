package com.picpay.desafio.android.domain.repository

import com.picpay.android.common.coroutines.CoroutineTestRule
import com.picpay.desafio.android.commontest.coVerifyOnce
import com.picpay.desafio.android.commontest.relaxedMock
import com.picpay.desafio.android.feature.contacts.data.datasource.local.ContactLocalSource
import com.picpay.desafio.android.feature.contacts.data.datasource.remote.ContactsRemote
import com.picpay.desafio.android.feature.contacts.data.repository.ContactsRepository
import com.picpay.desafio.android.feature.contacts.domain.repository.ContactsRepositoryImpl
import com.picpay.desafio.android.util.ContactsHelperTest.getFakeContacts
import com.picpay.desafio.android.util.ContactsHelperTest.getFakeContactsEntity
import com.picpay.desafio.android.util.ContactsHelperTest.getFakeContactsResponse
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException
import kotlin.test.assertFailsWith

@OptIn(ExperimentalCoroutinesApi::class)
class ContactsRepositoryTest {

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    private var remote: ContactsRemote = relaxedMock()
    private var local: ContactLocalSource = relaxedMock()

    private lateinit var repository: ContactsRepository

    @Before
    fun setUp() {
        clearAllMocks()

        repository = ContactsRepositoryImpl(
            remote = remote,
            local = local,
            dispatchers = coroutineRule.dispatchers,
        )
    }

    @Test
    fun `get contacts should return local contacts when remote fetch is successful`() = runTest {
        val contactsResponse = getFakeContactsResponse()
        val contactsEntity = getFakeContactsEntity()
        val expectedContacts = getFakeContacts()

        coEvery { remote.getContacts() } returns contactsResponse
        coEvery { local.getContactsLocal() } returns contactsEntity

        val result = repository.getContacts()

        assertEquals(expectedContacts, result)
        coVerifyOnce {
            local.getContactsLocal()
            local.saveContacts(contactsEntity)
        }

    }

    @Test
    fun `get contacts should return local contacts when remote fetch throws exception but local data exists`() = runTest {
        val contactsEntity = getFakeContactsEntity()
        val expectedContacts = getFakeContacts()

        coEvery { remote.getContacts() } throws IOException()
        coEvery { local.getContactsLocal() } returns contactsEntity

        // Act
        val result = repository.getContacts()

        // Assert
        assertEquals(expectedContacts, result)
    }

    @Test
    fun `getContacts should throw exception when remote fetch and local fetch fail`() = runTest {
        // Arrange
        coEvery { remote.getContacts() } throws IOException()
        coEvery { local.getContactsLocal() } returns emptyList()

        assertFailsWith<Exception> { repository.getContacts() }
        coVerifyOnce { local.getContactsLocal() }
    }

    @Test
    fun `refreshContactsFromRemote should save contacts to local source`() = runTest {
        // Arrange
        val contactResponses = getFakeContactsResponse()
        val contactEntities = getFakeContactsEntity()

        coEvery { remote.getContacts() } returns contactResponses

        // Act
        repository.getContacts()

        coVerifyOnce { local.saveContacts(contactEntities) }
    }


}