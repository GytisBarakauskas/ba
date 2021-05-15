package com.gytisdev.bahometask.application.common

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

inline fun <T> LiveData<TaskStatus<T>>.observe(
    lifecycleOwner: LifecycleOwner,
    crossinline onLoading: () -> Unit = {},
    crossinline onSuccess: (message: String) -> Unit = {},
    crossinline onSuccessWithResult: (result: T) -> Unit = {},
    crossinline onError: (error: Throwable) -> Unit = {},
    crossinline onErrorMessage: (error: String) -> Unit = {}
) {
    this.observe(lifecycleOwner, Observer {
        when (it) {
            is TaskStatus.Loading -> onLoading()
            is TaskStatus.Success -> onSuccess(it.message)
            is TaskStatus.SuccessWithResult -> onSuccessWithResult(it.result)
            is TaskStatus.Failure -> onErrorMessage(it.errorMessage)
            is TaskStatus.FailureWithException -> onError(it.error)
        }
    })
}