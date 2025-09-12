package com.tarasovvp.kmpuserlist.di

import android.content.Context
import org.koin.core.module.Module

fun configureAndroid(context: Context) {
    DIBridge.configure { androidModule(context) }
}

actual suspend fun defaultPlatformModule(): Module = error("Call configureAndroid(context) before first resolve()")