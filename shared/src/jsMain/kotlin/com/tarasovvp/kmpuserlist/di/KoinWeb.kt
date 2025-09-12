package com.tarasovvp.kmpuserlist.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

fun doInitKoin(): KoinApplication =
    startKoin {
        modules(commonModule, webModule)
    }
