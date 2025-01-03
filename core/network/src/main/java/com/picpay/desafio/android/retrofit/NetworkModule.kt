package com.picpay.desafio.android.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.providers.NetworkConfigurationProvider
import okhttp3.OkHttpClient
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {
    @Single
    fun provideGson(): Gson = GsonBuilder().create()

    @Single
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    @Single
    fun provideRetrofit(
        gson: Gson,
        okHttpClient: OkHttpClient,
        networkConfigProvider: NetworkConfigurationProvider
    ): Retrofit = Retrofit.Builder()
        .baseUrl(networkConfigProvider.getBaseUrl())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()

}
