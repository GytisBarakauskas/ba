package com.gytisdev.bahometask.application.common

sealed class Outcome<T>(
    val data: T? = null,
    val error: Throwable? = null
) {
    class Success<T>(data: T) : Outcome<T>(data)
    class Loading<T>(data: T? = null) : Outcome<T>(data)
    class Error<T>(throwable: Throwable, data: T? = null) : Outcome<T>(data, throwable)
}
