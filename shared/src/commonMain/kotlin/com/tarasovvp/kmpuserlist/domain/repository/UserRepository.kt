package com.tarasovvp.kmpuserlist.domain.repository

import com.tarasovvp.kmpuserlist.domain.model.User

interface UserRepository {
    suspend fun getUsers(forceRefresh: Boolean = false): List<User>
}