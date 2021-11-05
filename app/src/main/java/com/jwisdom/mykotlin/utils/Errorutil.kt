package com.jwisdom.mykotlin.utils

import com.jwisdom.mykotlin.data.ApiError
import com.jwisdom.mykotlin.retrofit.RetrofitClient
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException


class Errorutil {
    fun parseError(response: Response<*>): ApiError {
        val converter: Converter<ResponseBody, ApiError> = RetrofitClient.retrofit_build
            .responseBodyConverter(ApiError::class.java, arrayOfNulls<Annotation>(0))
        val error: ApiError = try {
            converter.convert(response.errorBody())!!
        } catch (e: IOException) {
            return ApiError(0,e.toString())
        }
        return error
    }
}