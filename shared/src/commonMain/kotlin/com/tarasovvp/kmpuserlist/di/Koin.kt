@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.tarasovvp.kmpuserlist.di

import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module

private var customConfig: (KoinApplication.() -> Unit)? = null

fun initKoin(config: (KoinApplication.() -> Unit) = {}) {
    customConfig = config
}
private val koinInstance by lazy {
    startKoin {
        customConfig?.invoke(this)
        modules(commonModule, platformModule())
    }.koin
}

fun getKoin(): Koin = koinInstance

expect fun platformModule(): Module
