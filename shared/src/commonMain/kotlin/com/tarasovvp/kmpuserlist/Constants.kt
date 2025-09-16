package com.tarasovvp.kmpuserlist

object Constants {
    const val BASE_URL = "https://dummyjson.com/"
    const val USERS_ENDPOINT = "users"
    const val USER_DB = "user.db"
    const val SQL_JS_WORKER_URL =
        """new URL("@cashapp/sqldelight-sqljs-worker/sqljs.worker.js", import.meta.url)"""
    const val DATABASE_NOT_INITIALIZED = "Database is not initialized"
}
