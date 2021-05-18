package com.gytisdev.bahometask.application.common

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OutcomeConverter @Inject constructor(
    private val errorHelper: ErrorHelper
) {

    fun <T> toTaskStatusWithResolvedError(outcome: Outcome<T>): TaskStatus<T> {
        return when (outcome) {
            is Outcome.Loading -> {
                TaskStatus.loading(outcome.data)
            }
            is Outcome.Success -> {
                TaskStatus.success(outcome.data!!)
            }
            is Outcome.Error -> {
                TaskStatus.failure(errorHelper.getErrorMessage(outcome.error), outcome.data)
            }
        }
    }
}