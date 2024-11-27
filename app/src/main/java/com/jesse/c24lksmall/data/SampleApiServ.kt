package com.jesse.c24lksmall.data

import com.jesse.c24lksmall.data.model.ResponseSample
import retrofit2.Response
import retrofit2.http.GET

interface SampleApiServ {
    @GET("characters")
    suspend fun getData(): Response<ResponseSample>

}