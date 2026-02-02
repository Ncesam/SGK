package com.ncesam.sgk2026.data.remote.utils

import retrofit2.HttpException
import retrofit2.Response


suspend fun <K, T> safeApiCall(
	call: suspend () -> Response<T>,
	mapper: (T) -> K
): Result<K> {
	try {
		val response = call()
		if (response.isSuccessful) {
			val body = response.body()
			if (body != null) {
				val mapped = mapper(body)
				return Result.success(mapped)
			} else {
				return Result.failure(Exception("Response body are null"))
			}
		} else {
			return Result.failure(HttpException(response))
		}
	} catch (e: Exception) {
		return Result.failure(Exception(e))
	}
}