package com.tarasovvp.kmpuserlist.di

import com.tarasovvp.kmpuserlist.domain.usecase.GetUserListUseCase

suspend fun getUserListUseCase(): GetUserListUseCase = getKoin().get()