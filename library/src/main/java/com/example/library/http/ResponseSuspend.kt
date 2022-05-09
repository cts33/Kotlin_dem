package com.example.library.http

import com.example.library.http.ResponseSuspend.EXCEPTION_CODE_IO
import com.example.library.http.ResponseSuspend.EXCEPTION_CODE_UNKNOWN
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.lang.Exception

object ResponseSuspend {
    const val EXCEPTION_CODE_IO = 11
    const val EXCEPTION_CODE_UNKNOWN = 2

    suspend fun <T> handle(request: suspend () -> ResponseResult<T>): ResponseResult<T> {
        return try {
            request.invoke()
        } catch (exception: Exception) {
            handleException(exception)
        }
    }

    suspend fun <T> handleWithHeaders(request: suspend () -> Response<ResponseResult<T>>): ResponseResult<T> {
        var apiResponse: ResponseResult<T>? = null
        try {
            val response = request.invoke()

            if (response.isSuccessful) {
                apiResponse = response.body()
            } else {
                apiResponse = ResponseResult<T>(
                    response.code(),
                    response.message(),
                    arrayListOf()
                )
            }

        } catch (exception: Exception) {
            return handleException(exception)
        }
        return apiResponse!!
    }

    private fun <T> handleException(exception: Exception): ResponseResult<T> {
        exception.printStackTrace()
        return when (exception) {
            is IOException -> {
                ResponseResult(
                    EXCEPTION_CODE_IO,
                    exception.message!!,
                    arrayListOf()
                )
            }
            is HttpException -> {
                ResponseResult(
                    exception.code(),
                    exception.message!!,
                    arrayListOf()
                )
            }
            else -> {
                ResponseResult(
                    EXCEPTION_CODE_UNKNOWN,
                    exception.message!!,
                    arrayListOf()
                )
            }
        }
    }
}