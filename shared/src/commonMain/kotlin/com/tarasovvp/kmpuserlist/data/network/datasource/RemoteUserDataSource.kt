package com.tarasovvp.kmpuserlist.data.network.datasource

import com.tarasovvp.kmpuserlist.data.network.model.RemoteUser

interface RemoteUserDataSource {
    suspend fun fetchUsers(): List<RemoteUser>
}