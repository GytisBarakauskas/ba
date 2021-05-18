package com.gytisdev.bahometask.application.preferences

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SyncPreferences @Inject constructor(private val preferences: SharedPreferences) {
    var lastPostsListSyncTimestamp: Long
        get() = preferences.getLong(lastPostsSyncTimestampKey, -1L)
        set(value) {
            preferences.edit().apply {
                putLong(lastPostsSyncTimestampKey, value)
                apply()
            }
        }

    private companion object {
        val lastPostsSyncTimestampKey = "lastPostsSync"
    }
}