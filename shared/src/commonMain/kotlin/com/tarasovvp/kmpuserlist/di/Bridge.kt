package com.tarasovvp.kmpuserlist.di

import com.tarasovvp.kmpuserlist.domain.usecase.GetUserListUseCase
import org.koin.core.Koin

fun getKoin(): Koin = getAppKoin()

suspend fun getUserListUseCase(): GetUserListUseCase = getKoin().get()