package com.tarasovvp.kmpuserlist.data.database

import com.tarasovvp.kmpuserlist.Constants
import com.tarasovvp.kmpuserlist.db.UserDatabase

class SharedDatabase(private val databaseDriverFactory: DatabaseDriverFactory) {
    private var database: UserDatabase? = null

    private suspend fun initDatabase() {
        database.takeIf { it != null } ?: run {
            database = UserDatabase(databaseDriverFactory.createDriver())
        }
    }

    suspend operator fun <R> invoke(block: suspend (UserDatabase) -> R): R {
        initDatabase()
        return database.takeIf { it != null }?.let {
            block(it)
        } ?: error(Constants.DATABASE_NOT_INITIALIZED)
    }
}
