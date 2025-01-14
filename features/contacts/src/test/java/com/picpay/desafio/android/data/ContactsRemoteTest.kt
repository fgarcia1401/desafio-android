package com.picpay.desafio.android.data

import com.picpay.desafio.android.commontest.coVerifyOnce
import com.picpay.desafio.android.commontest.relaxedMock
import com.picpay.desafio.android.feature.contacts.data.api.PicPayService
import com.picpay.desafio.android.feature.contacts.data.datasource.remote.ContactsRemoteImpl
import com.picpay.desafio.android.feature.contacts.data.entities.UserResponse
import com.picpay.desafio.android.util.ContactsHelperTest.getFakeContactsResponse
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class ContactsRemoteTest {

    private val service: PicPayService = relaxedMock()

    private lateinit var remote: ContactsRemoteImpl

    @Before
    fun setUp() {
        clearAllMocks()
        remote = ContactsRemoteImpl(service)
    }

    @Test
    fun `get contacts should return list of ContactResponse on success`() = runTest {
        val expectedContacts = getFakeContactsResponse()
        coEvery { service.getUsers() } returns expectedContacts

        val result = remote.getContacts()

        assertEquals(expectedContacts, result)
        coVerifyOnce { service.getUsers() }
    }

    @Test(expected = IOException::class)
    fun `get contacts should throw IOException on network error`() = runTest {
        coEvery { service.getUsers() } throws IOException()

        remote.getContacts()
    }

    @Test(expected = HttpException::class)
    fun `getContacts should throw HttpException on HTTP error`() = runTest {
        val errorBody = "{\"error\": \"Not Found\"}".toResponseBody("application/json".toMediaType())
        val errorResponse = Response.error<List<UserResponse>>(404, errorBody)

        coEvery { service.getUsers() } throws HttpException(errorResponse)

        remote.getContacts()
    }
    

    @After
    fun tearDown() = unmockkAll()

}