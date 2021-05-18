package com.gytisdev.bahometask.application.common

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

inline fun <T> LiveData<TaskStatus<T>>.observe(
    lifecycleOwner: LifecycleOwner,
    crossinline onLoading: (data: T?) -> Unit = {},
    crossinline onSuccess: (result: T) -> Unit = {},
    crossinline onErrorMessage: (errorMessage: String, data: T?) -> Unit = { _, _ -> }
) {
    this.observe(lifecycleOwner, Observer {
        when (it) {
            is TaskStatus.Loading -> onLoading(it.result)
            is TaskStatus.Success -> onSuccess(it.result)
            is TaskStatus.Failure -> onErrorMessage(it.errorMessage, it.data)
        }
    })
}