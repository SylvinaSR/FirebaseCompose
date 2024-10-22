package com.sylviepractices.firebasecompose.domain

import com.sylviepractices.firebasecompose.data.LoginRepository
import com.sylviepractices.firebasecompose.model.ErrorModel
import com.sylviepractices.firebasecompose.model.ResultModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: LoginRepository
) {

    operator fun invoke(email: String, password: String): Flow<ResultModel<Unit, ErrorModel>> =
        flow {
            val result = repository.doLogin(email = email, password = password)
            if (result) emit(ResultModel.Success(Unit)) else emit(ResultModel.Error(ErrorModel.ERROR))
        }


}