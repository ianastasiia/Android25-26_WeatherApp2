package ru.kpfu.itis.android.core.network.util

import ru.kpfu.itis.android.core.network.model.ApiResult

suspend inline fun <T> safeApiCall(
    crossinline call: suspend () -> T
): ApiResult<T> {
    return try {
        ApiResult.Success(call())
    } catch (t: Throwable) {
        ApiResult.Failure(t)
    }

}