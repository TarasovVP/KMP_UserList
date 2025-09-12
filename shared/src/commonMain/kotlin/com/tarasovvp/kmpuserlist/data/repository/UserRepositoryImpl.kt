package com.tarasovvp.kmpuserlist.data.repository

import com.tarasovvp.kmpuserlist.data.database.datasource.LocalUserDataSource
import com.tarasovvp.kmpuserlist.data.mapper.toDomain
import com.tarasovvp.kmpuserlist.data.network.datasource.RemoteUserDataSource
import com.tarasovvp.kmpuserlist.domain.model.User
import com.tarasovvp.kmpuserlist.domain.repository.UserRepository

class UserRepositoryImpl(
    private val remote: RemoteUserDataSource,
    private val local: LocalUserDataSource
) : UserRepository {
    override suspend fun getUsers(forceRefresh: Boolean): List<User> {
        val cached = local.getUsers()
        if (cached.isNotEmpty() && !forceRefresh) return cached
        val fresh = remote.fetchUsers().map { it.toDomain() }
        local.replaceAll(fresh)
        return fresh
    }
}