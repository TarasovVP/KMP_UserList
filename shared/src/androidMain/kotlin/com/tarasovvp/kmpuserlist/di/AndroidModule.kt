package com.tarasovvp.kmpuserlist.di

import com.tarasovvp.kmpuserlist.data.database.DatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidModule =
    module {
        single {
            DatabaseDriverFactory(androidContext())
        }
    }
