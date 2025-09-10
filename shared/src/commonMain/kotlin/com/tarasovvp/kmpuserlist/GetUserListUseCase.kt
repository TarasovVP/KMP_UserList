package com.tarasovvp.kmpuserlist

import kotlinx.coroutines.delay

class GetUserListUseCase {
    suspend fun execute(): List<User> {
        delay(1000)

        return listOf(
            User(
                firstName = "Emily",
                lastName = "Johnson",
                maidenName = "Smith",
                age = 28,
                gender = "female",
                email = "emily.johnson@x.dummyjson.com",
                phone = "+81 965-431-3024",
                birthDate = "1996-5-30",
                image = "https://dummyjson.com/icon/emilys/128"
            ),
            User(
                firstName = "Michael",
                lastName = "Brown",
                maidenName = "",
                age = 35,
                gender = "male",
                email = "michael.brown@x.dummyjson.com",
                phone = "+44 7700 900123",
                birthDate = "1989-11-20",
                image = "https://dummyjson.com/icon/michaels/128"
            )
        )
    }
}