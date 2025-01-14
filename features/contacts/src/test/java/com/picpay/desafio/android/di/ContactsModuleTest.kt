package com.picpay.desafio.android.di

import android.content.Context
import com.picpay.android.common.coroutines.CustomDispatchers
import com.picpay.desafio.android.commontest.relaxedMock
import com.picpay.desafio.android.core.network.BuildConfig
import com.picpay.desafio.android.feature.contacts.data.api.PicPayService
import com.picpay.desafio.android.feature.contacts.data.database.ContactDataBase
import com.picpay.desafio.android.feature.contacts.data.datasource.local.ContactDao
import com.picpay.desafio.android.feature.contacts.data.datasource.local.ContactLocalSource
import com.picpay.desafio.android.feature.contacts.data.datasource.remote.ContactsRemote
import com.picpay.desafio.android.feature.contacts.data.repository.ContactsRepository
import com.picpay.desafio.android.feature.contacts.di.ContactsModule
import com.picpay.desafio.android.feature.contacts.domain.usecase.fetchContacts.FetchContactsUseCase
import com.picpay.desafio.android.feature.contacts.presentation.list.ContactsViewModel
import io.mockk.mockk
import org.junit.After
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.ksp.generated.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import retrofit2.Retrofit

class ContactsModuleTest : KoinTest {

    @Test
    fun `check contacts modules definitions bounding`() {

        val requiredDependencies = module {
            factory<ContactsRepository> { relaxedMock() }
            factory<ContactsRemote> { relaxedMock() }
            factory<ContactsRemote> { relaxedMock() }
            factory<ContactLocalSource> { relaxedMock() }
            single<Context> { relaxedMock() }
            single { mockk<ContactDataBase>() }
            single { mockk<ContactDao>() }
            single<CustomDispatchers> { relaxedMock() }
            factory<FetchContactsUseCase> { relaxedMock() }
            factory<ContactsViewModel> { relaxedMock() }

            single {
                Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .build()
            }
            single<PicPayService> {
                val retrofit: Retrofit = get()
                retrofit.create(PicPayService::class.java)
            }
        }

        startKoin {
            modules(requiredDependencies)
            modules(ContactsModule().module)
        }.checkModules()
    }

    @After
    fun after() = stopKoin()
}
