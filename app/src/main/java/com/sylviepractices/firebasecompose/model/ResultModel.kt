package com.sylviepractices.firebasecompose.model

sealed class ResultModel<out T, out E> {
    data class Success<out T>(val data: T) : ResultModel<T, Nothing>()
    data class Error<out E>(val error: E) : ResultModel<Nothing, E>()
}