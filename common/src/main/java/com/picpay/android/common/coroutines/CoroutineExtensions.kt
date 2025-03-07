package com.picpay.android.common.coroutines

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
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
    runCatching {
        collect { value ->
            success(value)
        }
    }.onFailure { throwable -> onError(throwable) }
     .also { onCompletion() }
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
