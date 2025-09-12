package com.tarasovvp.kmpuserlist.di

import com.tarasovvp.kmpuserlist.data.database.SharedDatabase
import com.tarasovvp.kmpuserlist.data.database.datasource.LocalUserDataSource
import com.tarasovvp.kmpuserlist.data.database.datasource.LocalUserDataSourceImpl
import com.tarasovvp.kmpuserlist.data.network.datasource.RemoteUserDataSource
import com.tarasovvp.kmpuserlist.data.network.datasource.RemoteUserDataSourceImpl
import com.tarasovvp.kmpuserlist.data.repository.UserRepositoryImpl
import com.tarasovvp.kmpuserlist.domain.repository.UserRepository
import com.tarasovvp.kmpuserlist.domain.usecase.GetUserListUseCase
import com.tarasovvp.kmpuserlist.domain.usecase.GetUserListUseCaseImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val commonModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }
    }
    single<RemoteUserDataSource> { RemoteUserDataSourceImpl(get()) }
    single {
        SharedDatabase(get())
    }
    single<LocalUserDataSource> { LocalUserDataSourceImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get(), get()) }
    single<GetUserListUseCase> { GetUserListUseCaseImpl(get()) }
}