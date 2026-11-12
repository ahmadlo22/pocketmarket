package com.ahmad.pocketmarker.domain.model

sealed interface AppError{
    data object NoInternet : AppError
    data object Timeout : AppError
    data class Http(val code : Int): AppError
    data class  Unknown( val message : String?) : AppError
}
sealed interface AppResult<out T>{
    data class Success<T>(val data:T) : AppResult<T>
    data class Failure(val error: AppError) : AppResult<Nothing>
}