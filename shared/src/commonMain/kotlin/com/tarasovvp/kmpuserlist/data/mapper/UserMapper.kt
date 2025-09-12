package com.tarasovvp.kmpuserlist.data.mapper

import com.tarasovvp.kmpuserlist.data.network.model.RemoteUser
import com.tarasovvp.kmpuserlist.domain.model.User

fun RemoteUser.toDomain() = User(
    firstName = firstName,
    lastName = lastName,
    maidenName = maidenName,
    age = age,
    gender = gender,
    email = email,
    phone = phone,
    birthDate = birthDate,
    image = image
)