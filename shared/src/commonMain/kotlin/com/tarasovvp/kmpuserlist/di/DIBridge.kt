package com.tarasovvp.kmpuserlist.di

import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import kotlin.reflect.KClass

object DIBridge {
    private var app: KoinApplication? = null
    private var provider: (suspend () -> Module)? = null

    @PublishedApi
    internal suspend fun ensure(): Koin {
        val a = app
        if (a != null) return a.koin
        val module = provider?.invoke() ?: defaultPlatformModule()
        val started = startKoin { modules(commonModule, module) }
        app = started
        return started.koin
    }

    suspend fun <T : Any> get(klass: KClass<T>): T = ensure().get(klass)
    suspend inline fun <reified T : Any> get(): T = ensure().get()
}

expect suspend fun defaultPlatformModule(): Module