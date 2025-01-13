package com.picpay.desafio.android.commontest

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceTimeBy

const val DEFAULT_TIMEOUT = 1000L

typealias EventMap = Pair<TestScope, List<MutableList<Any>>>

@Suppress("UNREACHABLE_CODE")
fun flowException(throwable: Throwable = Exception()) = flow {
    emit(throw throwable)
}

fun TestScope.observe(
    vararg flows: Flow<Any>,
    action: () -> Unit,
): EventMap {
    val indexedResult = mutableMapOf<Int, MutableList<Any>>()

    flows.forEachIndexed { index, flow ->
        indexedResult[index] = mutableListOf()
        backgroundScope.launch {
            flow.collect { value ->
                indexedResult[index]?.add(value)
            }
        }
    }

    observe { action() }
    return EventMap(this, indexedResult.values.toList())
}

@OptIn(ExperimentalCoroutinesApi::class)
fun TestScope.observe(
    action: () -> Unit,
) {
    action()
    advanceTimeBy(DEFAULT_TIMEOUT)
}

inline fun <reified T> EventMap.withLastEvent(crossinline action: (event: T) -> Unit = {}): EventMap {
    filterEventByType<T>().apply {
        val event = this?.lastOrNull { it is T }
        this?.remove(event)
        requireNotNull(event) { "nenhum flow emitiu o tipo ${T::class.java}" }
        first.observe { action.invoke(event as T) }
    }

    return this
}

inline fun <reified T> EventMap.withFirstEvent(crossinline action: (event: T) -> Unit = {}): EventMap {
    filterEventByType<T>().apply {
        val event = this?.firstOrNull { it is T }
        this?.remove(event)
        requireNotNull(event) { "nenhum flow emitiu o tipo ${T::class.java}" }
        first.observe { action.invoke(event as T) }
    }

    return this
}

inline fun <reified T> EventMap.hasNotEvent(action: () -> Unit = {}): EventMap {
    filterEventByType<T>().apply {
        val event = this?.firstOrNull { it is T }
        if (event != null) {
            throw IllegalArgumentException("algum flow emitiu o tipo ${T::class.java}")
        } else {
            action.invoke()
        }
    }

    return this
}


inline fun <reified T> EventMap.filterEventByType() =
    second.firstOrNull { lists -> lists.any { it is T } }

