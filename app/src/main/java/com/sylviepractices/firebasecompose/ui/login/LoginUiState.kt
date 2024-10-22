package com.sylviepractices.firebasecompose.ui.login

sealed interface LoginUiState {

    data object Start: LoginUiState
    data object Loading: LoginUiState
    data object Success: LoginUiState
    data object Error: LoginUiState

}