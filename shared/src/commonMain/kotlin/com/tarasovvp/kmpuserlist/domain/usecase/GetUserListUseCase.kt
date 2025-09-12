package com.tarasovvp.kmpuserlist.domain.usecase

import com.tarasovvp.kmpuserlist.domain.model.User

interface GetUserListUseCase {
    suspend fun execute(): List<User>
}