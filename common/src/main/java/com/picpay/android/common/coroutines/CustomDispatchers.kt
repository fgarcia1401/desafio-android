package com.picpay.android.common.coroutines

import kotlinx.coroutines.CoroutineDispatcher

interface CustomDispatchers {
    fun io(): CoroutineDispatcher
    fun main(): CoroutineDispatcher
    fun default(): CoroutineDispatcher
}
