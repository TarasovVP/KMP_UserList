package com.tarasovvp.kmpuserlist.di

import com.tarasovvp.kmpuserlist.data.database.DatabaseDriverFactory
import org.koin.dsl.module

val iosModule =
    module {
        single {
            DatabaseDriverFactory()
        }
    }
