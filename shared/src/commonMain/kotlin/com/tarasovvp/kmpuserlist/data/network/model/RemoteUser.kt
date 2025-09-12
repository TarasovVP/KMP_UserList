package com.tarasovvp.kmpuserlist.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class RemoteUser(
    val firstName: String,
    val lastName: String,
    val maidenName: String,
    val age: Int,
    val gender: String,
    val email: String,
    val phone: String,
    val birthDate: String,
    val image: String
)