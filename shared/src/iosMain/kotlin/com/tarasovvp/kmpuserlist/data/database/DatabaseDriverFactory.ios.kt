package com.tarasovvp.kmpuserlist.data.database

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.tarasovvp.kmpuserlist.Constants
import com.tarasovvp.kmpuserlist.db.UserDatabase

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class DatabaseDriverFactory {
    actual suspend fun createDriver(): SqlDriver {
        return NativeSqliteDriver(UserDatabase.Schema.synchronous(), Constants.USER_DB)
    }
}