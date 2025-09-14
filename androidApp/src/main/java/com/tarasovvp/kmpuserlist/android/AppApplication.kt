package com.tarasovvp.kmpuserlist.android

import android.app.Application
import com.tarasovvp.kmpuserlist.di.initKoin

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(this)
    }
}