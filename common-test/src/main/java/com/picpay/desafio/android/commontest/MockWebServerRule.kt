package com.picpay.desafio.android.commontest

import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.MockResponse
import org.junit.rules.TestRule
import org.junit.runner.Description
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.runners.model.Statement
import java.net.HttpURLConnection.HTTP_OK

class MockWebServerRule : TestRule {
    val server = MockWebServer()

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                server.start(PORT)
                try {
                    base.evaluate()
                } finally {
                    server.shutdown()
                }
            }
        }
    }

    fun enqueueResponseFromFile(fileName: String, responseCode: Int = HTTP_OK) {
        val context = InstrumentationRegistry.getInstrumentation().context
        val json = TestUtils.getJsonFromFile(context, fileName)
        val response = MockResponse()
            .setResponseCode(responseCode)
            .setBody(json)
        server.enqueue(response)
    }

    fun enqueueErrorResponse(responseCode: Int) {
        val response = MockResponse()
            .setResponseCode(responseCode)
        server.enqueue(response)
    }

    private companion object {
        const val PORT = 50001
    }
}