package com.tarasovvp.kmpuserlist.di

import org.koin.core.module.Module

actual suspend fun defaultPlatformModule(): Module = iosModule