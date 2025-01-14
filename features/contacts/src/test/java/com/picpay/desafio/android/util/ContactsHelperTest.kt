package com.picpay.desafio.android.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.picpay.desafio.android.commontest.TestUtils.getJson
import com.picpay.desafio.android.feature.contacts.data.entities.ContactEntity
import com.picpay.desafio.android.feature.contacts.data.entities.UserResponse
import com.picpay.desafio.android.feature.contacts.domain.model.User

object ContactsHelperTest {

    fun getFakeContacts() =
        listOf(
            User(
                id = 1,
                name = "Sandrine Spinka",
                img = "https://randomuser.me/api/portraits/men/1.jpg",
                username = "Tod86"
            ),
            User(
                id = 2,
                name = "Carli Carroll",
                img = "https://randomuser.me/api/portraits/men/2.jpg",
                username = "Constantin_Sawayn"
            ),
        )

    fun getFakeContactsEntity() =
        listOf(
            ContactEntity(
                id = 1,
                name = "Sandrine Spinka",
                img = "https://randomuser.me/api/portraits/men/1.jpg",
                username = "Tod86"
            ),
            ContactEntity(
                id = 2,
                name = "Carli Carroll",
                img = "https://randomuser.me/api/portraits/men/2.jpg",
                username = "Constantin_Sawayn"
            ),
        )

    fun getFakeContactsResponse(): List<UserResponse> {
        val type = object : TypeToken<List<UserResponse>>() {}.type
        val json = this::class.java.classLoader?.let {
            getJson("fake_contacts_response.json", it)
        }
        return Gson().fromJson(json, type)
    }
}