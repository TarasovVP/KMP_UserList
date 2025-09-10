package com.tarasovvp.kmpuserlist.android.presentation

import com.tarasovvp.kmpuserlist.User

data class UiState(
    val isLoading: Boolean = true,
    val users: List<User> = emptyList(),
    val error: String? = null
)
