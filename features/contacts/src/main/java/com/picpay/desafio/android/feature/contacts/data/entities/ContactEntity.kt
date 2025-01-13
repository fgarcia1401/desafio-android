package com.picpay.desafio.android.feature.contacts.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class ContactEntity(
    @PrimaryKey val id: Int,
    val img: String? = null,
    val name: String? = null,
    val username: String? = null
)