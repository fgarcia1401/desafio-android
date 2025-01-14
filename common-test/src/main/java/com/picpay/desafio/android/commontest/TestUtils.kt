package com.picpay.desafio.android.commontest

import android.content.Context
import java.io.IOException
import java.nio.charset.StandardCharsets

object TestUtils {

    fun getJson(fileName: String, classLoader: ClassLoader): String? {
        return try {
            val inputStream = classLoader.getResourceAsStream(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)

            inputStream.read(buffer)
            inputStream.close()

            String(buffer, StandardCharsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    fun getJsonFromFile(context: Context, fileName: String): String {
        return try {
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            ""
        }
    }

}
