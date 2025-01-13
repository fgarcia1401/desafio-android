package com.picpay.desafio.android.feature.contacts.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.feature.contacts.data.datasource.local.ContactDao
import com.picpay.desafio.android.feature.contacts.data.entities.ContactEntity

@Database(entities = [ContactEntity::class], version = 1, exportSchema = false)
abstract class ContactDataBase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
}