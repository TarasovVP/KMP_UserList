package com.tarasovvp.kmpuserlist.data.database.datasource

import com.tarasovvp.kmpuserlist.domain.model.User

interface LocalUserDataSource {
    suspend fun getUsers(): List<User>
    suspend fun replaceAll(users: List<User>)
}