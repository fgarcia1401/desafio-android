package com.picpay.desafio.android.data

import com.picpay.desafio.android.commontest.coVerifyOnce
import com.picpay.desafio.android.commontest.relaxedMock
import com.picpay.desafio.android.feature.contacts.data.datasource.local.ContactDao
import com.picpay.desafio.android.feature.contacts.data.datasource.local.ContactLocalSourceImpl
import com.picpay.desafio.android.util.ContactsHelperTest.getFakeContactsEntity
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlinx.coroutines.test.runTest
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class ContactsLocalTest {

    private val contactDao: ContactDao = relaxedMock()

    private lateinit var local: ContactLocalSourceImpl

    @Before
    fun setUp() {
        clearAllMocks()
        local = ContactLocalSourceImpl(contactDao)
    }

    @Test
    fun `getContactsLocal should return contacts from DAO`() = runTest {
        val expectedContacts = getFakeContactsEntity()

        coEvery { contactDao.getAllContacts() } returns flowOf(expectedContacts)

        val result = local.getContactsLocal()

        assertEquals(expectedContacts, result)
        coVerifyOnce { contactDao.getAllContacts() }
    }

    @Test
    fun `save contacts should delete all existing contacts and insert new ones`() = runTest {
        val contactsToSave = getFakeContactsEntity()

        coEvery { contactDao.deleteAllContacts() } returns Unit
        coEvery { contactDao.insertAll(contactsToSave) } returns Unit

        local.saveContacts(contactsToSave)

        coVerifyOnce { contactDao.deleteAllContacts() }
        coVerifyOnce { contactDao.insertAll(contactsToSave) }
    }

    @After
    fun tearDown() = unmockkAll()
}
