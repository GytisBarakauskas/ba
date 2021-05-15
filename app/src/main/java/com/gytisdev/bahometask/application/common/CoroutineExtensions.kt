package com.gytisdev.bahometask.application.common

import kotlinx.coroutines.*

fun <T : Any> CoroutineScope.launch(
    work: suspend CoroutineScope.() -> T?,
    onSuccess: ((T?) -> Unit),
    onError: ((error: Throwable) -> Unit) = { }
): Job {
    val errorHandler = CoroutineExceptionHandler { _, throwable ->
        launch {
            onError(throwable)
        }
    }
    return launch(errorHandler) {
        val data = async rt@{
            return@rt work()
        }.await()
        onSuccess(data)
    }
}