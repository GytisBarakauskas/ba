package com.gytisdev.bahometask

import android.app.Application
import com.gytisdev.bahometask.application.storage.RealmConfigurator
import dagger.hilt.android.HiltAndroidApp
import io.realm.Realm

@HiltAndroidApp
class BAHometaskApplication : Application() {

    lateinit var realmConfigurator: RealmConfigurator

    override fun onCreate() {
        super.onCreate()
        realmConfigurator.initialize()
    }
}