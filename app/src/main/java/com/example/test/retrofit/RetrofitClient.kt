package com.example.test.retrofit

import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {


//    val Base_Url="https://www.ttrta.org/api/"
    val Base_Url="https://opendata.cwb.gov.tw/api/v1/rest/datastore/"

    val client : OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .connectionPool(ConnectionPool(0,5,TimeUnit.SECONDS))
            .build()
    }



//    client.connectTimeout(15, TimeUnit.SECONDS);
//    client.readTimeout(15, TimeUnit.SECONDS);
//    client.writeTimeout(15, TimeUnit.SECONDS);

    val retrofit :ApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl(Base_Url)
//            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create(ApiInterface::class.java)
    }


}