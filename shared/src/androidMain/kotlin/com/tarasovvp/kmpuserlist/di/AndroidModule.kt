package com.tarasovvp.kmpuserlist.di

import android.content.Context
import com.tarasovvp.kmpuserlist.data.database.DatabaseDriverFactory
import org.koin.dsl.module

fun androidModule(context: Context) =
    module {
        single {
            DatabaseDriverFactory(context)
        }
    }
