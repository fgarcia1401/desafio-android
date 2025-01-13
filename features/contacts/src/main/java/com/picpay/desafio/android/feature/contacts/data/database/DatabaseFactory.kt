package com.picpay.desafio.android.feature.contacts.data.database

import android.content.Context
import androidx.room.Room

object DatabaseFactory {

    private var instance: ContactDataBase? = null

    fun getDatabase(context: Context): ContactDataBase {
        return instance ?: synchronized(this) {
            val newInstance = Room.databaseBuilder(
                context.applicationContext,
                ContactDataBase::class.java,
                "contacts_database")
                .fallbackToDestructiveMigration()
                .build()
            instance = newInstance
            newInstance
        }
    }
}