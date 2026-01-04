package ru.kpfu.itis.android.core.network.model

sealed interface ApiResult<out T> {
    data class Success<out T>(
        val data: T
    ) : ApiResult<T>

    data class Failure(
        val throwable: Throwable
    ) : ApiResult<Nothing>

}