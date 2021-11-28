package com.example.test.retrofit

import com.example.test.data.weather.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
//    @GET("serviceCategoryList")
//    suspend fun getPost() : Response<Post>

//    @Headers("Accept: application/json")
    @GET("F-C0032-001?")
    suspend fun getData(
    @Query("Authorization") Authorization: String?
                 ,@Query("locationName") locationName: Array<String>?
                , @Query("format") format: String?
                , @Query("elementName") elementName: Array<String>?
    ): Response<Weather>
}