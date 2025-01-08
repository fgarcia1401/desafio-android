package com.picpay.android.common.coroutines

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

suspend fun <T> Flow<T>.safeFlowCollect(
    success: suspend (T) -> Unit,
    onError: (Throwable) -> Unit,
    onStart: () -> Unit = {},
    onCompletion: () -> Unit = {}
) {
    onStart()
    try {
        collect { value ->
            success(value)
        }
    } catch (e: Throwable) {
        onError(e)
    } finally {
        onCompletion()
    }
}

fun <T> LifecycleOwner.observe(flow: Flow<T>, action: (t: T) -> Unit) {
    lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect { action(it) }
        }
    }
}

fun <T, V : ViewModel> Channel<T>.sendInScope(scope: V, vararg event: T) {
    scope.viewModelScope.launch {
        event.forEach {
            this@sendInScope.send(it)
        }
    }
}

@Composable
fun <T> Flow<T>.collectWithLifecycle(action: suspend (T) -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(key1 = lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            this@collectWithLifecycle.collect { value ->
                action(value)
            }
        }
    }
}
