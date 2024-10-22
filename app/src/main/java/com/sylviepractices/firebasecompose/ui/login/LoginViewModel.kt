package com.sylviepractices.firebasecompose.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sylviepractices.firebasecompose.domain.LoginUseCase
import com.sylviepractices.firebasecompose.model.ResultModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<LoginUiState>(LoginUiState.Start)
    val uiState = _uiState.asStateFlow()

    fun doLogin(email: String, password: String) {
        viewModelScope.launch {
            _uiState.update { LoginUiState.Loading }
            loginUseCase(email = email, password = password).collect { result ->
                when (result) {
                    is ResultModel.Error -> {
                        _uiState.update { LoginUiState.Error }
                    }

                    is ResultModel.Success -> {
                        _uiState.update { LoginUiState.Success }
                    }
                }
            }
        }
    }

    fun resetUiState() = _uiState.update { LoginUiState.Start }

}