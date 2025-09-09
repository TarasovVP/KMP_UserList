package com.tarasovvp.kmp_userlist

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform