package com.tarasovvp.kmpuserlist.data.database.datasource

import app.cash.sqldelight.async.coroutines.awaitAsList
import com.tarasovvp.kmpuserlist.data.database.SharedDatabase
import com.tarasovvp.kmpuserlist.data.mapper.toDomain
import com.tarasovvp.kmpuserlist.domain.model.User

class LocalUserDataSourceImpl(private val db: SharedDatabase) : LocalUserDataSource {
    override suspend fun getUsers(): List<User> = db { database ->
        database.userDatabaseQueries
            .selectAll()
            .awaitAsList()
            .map {
                it.toDomain()
            }
    }

    override suspend fun replaceAll(users: List<User>) = db { database ->
        val q = database.userDatabaseQueries
        q.transaction {
            q.deleteAll()
            users.forEach {
                q.insertUser(
                    email = it.email,
                    firstName = it.firstName,
                    lastName = it.lastName,
                    maidenName = it.maidenName,
                    age = it.age.toLong(),
                    gender = it.gender,
                    phone = it.phone,
                    birthDate = it.birthDate,
                    image = it.image
                )
            }
        }
    }
}