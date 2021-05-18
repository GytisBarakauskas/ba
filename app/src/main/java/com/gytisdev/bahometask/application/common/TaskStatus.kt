package com.gytisdev.bahometask.application.common

sealed class TaskStatus<out T> {

    data class Loading<out T>(val result: T?) : TaskStatus<T>()
    data class Failure<out T>(val errorMessage: String = "", val data: T?) : TaskStatus<T>()
    data class Success<out T>(val result: T) : TaskStatus<T>()

    companion object {
        fun <T> success(result: T) = Success(result = result)
        fun <T> failure(errorMessage: String = "unknown", data: T?) = Failure<T>(errorMessage, data)
        fun <T> loading(data: T?) = Loading<T>(data)
    }
}