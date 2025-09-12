package com.tarasovvp.kmpuserlist.domain.model

data class User(
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