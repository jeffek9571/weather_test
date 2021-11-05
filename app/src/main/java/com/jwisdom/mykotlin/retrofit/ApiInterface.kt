package com.jwisdom.mykotlin.retrofit

import com.jwisdom.mykotlin.data.Post
import com.jwisdom.mykotlin.data.xml.Content
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("serviceCategoryList")
    suspend fun getPost() : Response<Post>

    @GET("note.asp")
    suspend fun getXml() : Response<Content>
}