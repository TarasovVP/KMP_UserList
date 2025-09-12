package com.tarasovvp.kmpuserlist.di

import com.tarasovvp.kmpuserlist.domain.usecase.GetUserListUseCase

object Bridge {
    suspend inline fun <reified T : Any> resolve(): T = DIBridge.get()
}

suspend fun getUserListUseCase(): GetUserListUseCase = Bridge.resolve()