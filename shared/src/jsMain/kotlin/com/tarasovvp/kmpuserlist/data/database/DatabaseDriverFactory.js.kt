package com.tarasovvp.kmpuserlist.data.database

import app.cash.sqldelight.async.coroutines.awaitCreate
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.worker.WebWorkerDriver
import com.tarasovvp.kmpuserlist.Constants
import com.tarasovvp.kmpuserlist.db.UserDatabase
import org.w3c.dom.Worker

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class DatabaseDriverFactory {
    actual suspend fun createDriver(): SqlDriver {
        return WebWorkerDriver(
            Worker(
                js(Constants.SQL_JS_WORKER_URL),
            ),
        ).also { UserDatabase.Schema.awaitCreate(it) }
    }
}