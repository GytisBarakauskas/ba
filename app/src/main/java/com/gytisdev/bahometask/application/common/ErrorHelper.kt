package com.gytisdev.bahometask.application.common

import android.content.Context
import com.gytisdev.bahometask.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ErrorHelper @Inject constructor(@ApplicationContext private val context: Context) {

    fun getErrorMessage(error: Throwable?): String {
        // here we could resolve some human-readable error
        // just return a generic error message for now
        return context.getString(R.string.error_generic_unknown)
    }
}