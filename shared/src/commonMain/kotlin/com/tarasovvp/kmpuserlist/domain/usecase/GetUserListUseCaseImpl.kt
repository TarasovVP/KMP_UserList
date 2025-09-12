package com.tarasovvp.kmpuserlist.domain.usecase

import com.tarasovvp.kmpuserlist.domain.repository.UserRepository
import com.tarasovvp.kmpuserlist.domain.model.User

class GetUserListUseCaseImpl(private val repository: UserRepository): GetUserListUseCase {
    override suspend fun execute(): List<User> = repository.getUsers(false)
}