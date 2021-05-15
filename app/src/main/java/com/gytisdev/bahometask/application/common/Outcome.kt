package com.gytisdev.bahometask.application.common

sealed class Outcome<out T> {
    data class Success<out T>(val value: T) : Outcome<T>()
    data class Failure(val error: Error) : Outcome<Nothing>()
}