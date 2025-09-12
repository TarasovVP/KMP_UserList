@file:OptIn(ExperimentalMaterial3Api::class)

package com.tarasovvp.kmpuserlist.android.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun App() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val viewModel: UsersViewModel = viewModel(UsersViewModel::class)
        val state by viewModel.uiState.collectAsStateWithLifecycle()
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Users") }
                )
            }
        ) { innerPadding ->
            when {
                state.users.isEmpty() && state.isLoading -> {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                state.error != null -> {
                    ErrorView(
                        message = state.error.orEmpty(),
                        onRetry = { viewModel.initialize() }
                    )
                }

                else -> {
                    UsersScreen(
                        users = state.users,
                        isRefreshing = state.isLoading,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        { viewModel.initialize() }
                    )
                }
            }
        }
    }
}