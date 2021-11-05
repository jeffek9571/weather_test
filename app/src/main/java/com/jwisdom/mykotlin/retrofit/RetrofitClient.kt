package com.jwisdom.mykotlin.retrofit

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

object RetrofitClient {

//    companion object{
//        private var retrofit :Retrofit? = null
//
//        fun instance() : Retrofit {
//            if(retrofit==null){
//                retrofit = Retrofit.Builder()
//                    .baseUrl("https://www.ttrta.org/api/")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
//            }
//            return retrofit!!
//        }
//    }

    val retrofit :ApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.ttrta.org/api/")
//            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }

    val retrofit_build :Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.ttrta.org/api/")
//            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val retrofit_xml :ApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.w3school.com.cn/xml/")
//            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }

//    companion object{
//
//        val instance : ApiInterface
//            get() {
//                return RetrofitClient().retrofit
//            }
//
//    }


}