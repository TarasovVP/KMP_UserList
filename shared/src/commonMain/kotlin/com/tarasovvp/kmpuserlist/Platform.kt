package com.tarasovvp.kmpuserlist

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform