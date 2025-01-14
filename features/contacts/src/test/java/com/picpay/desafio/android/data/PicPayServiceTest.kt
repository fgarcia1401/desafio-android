package com.picpay.desafio.android.data

import com.picpay.desafio.android.feature.contacts.data.api.PicPayService
import com.picpay.desafio.android.feature.contacts.data.entities.UserResponse
import io.mockk.clearAllMocks
import io.mockk.unmockkAll
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@ExperimentalCoroutinesApi
class PicPayServiceTest {

    private lateinit var server: MockWebServer
    private lateinit var service: PicPayService

    @Before
    fun setUp() {
        clearAllMocks()
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PicPayService::class.java)
    }

    @Test
    fun `getUsers should make GET request to correct endpoint`() = runTest {
        val responseBody = """
            [
                {"id": 1, "name": "User 1", "img": "img1", "username": "username1"}
            ]
        """.trimIndent()
        val expected = UserResponse("img1", "User 1", 1, "username1")
        server.enqueue(
            MockResponse()
                .setBody(responseBody)
                .setResponseCode(200)
        )

        val response = service.getUsers()

        val request = server.takeRequest()
        assertEquals("/users", request.path)
        assertEquals("GET", request.method)
        assertEquals(1, response.size)
        assertEquals(expected, response.first())
    }

    @Test(expected = retrofit2.HttpException::class)
    fun `getUsers should throw HttpException on server error`() = runTest {
        // Arrange
        server.enqueue(MockResponse().setResponseCode(500))

        // Act
        service.getUsers()
    }

    @After
    fun tearDown() {
        unmockkAll()
        server.shutdown()
    }

}
