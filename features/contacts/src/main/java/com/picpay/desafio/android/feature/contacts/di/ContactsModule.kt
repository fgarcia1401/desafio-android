package com.picpay.desafio.android.feature.contacts.di

import android.content.Context
import com.picpay.desafio.android.feature.contacts.data.api.PicPayService
import com.picpay.desafio.android.feature.contacts.data.datasource.local.ContactDao
import com.picpay.desafio.android.feature.contacts.data.database.ContactDataBase
import com.picpay.desafio.android.feature.contacts.data.database.DatabaseFactory
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import retrofit2.Retrofit

@Module
@ComponentScan("com.picpay.desafio.android.feature.contacts")
class ContactsModule {
    @Single
    internal fun pixAutomaticApi(retrofit: Retrofit): PicPayService {
        return retrofit.create(PicPayService::class.java)
    }

    @Single
    internal fun provideAppDatabase(context: Context): ContactDataBase {
        return DatabaseFactory.getDatabase(context)
    }

    @Single
    internal fun provideContactDao(database: ContactDataBase): ContactDao {
        return database.contactDao()
    }
}