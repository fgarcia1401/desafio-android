package com.picpay.desafio.android.feature.contacts.di

import com.picpay.desafio.android.feature.contacts.data.api.PicPayService
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
}