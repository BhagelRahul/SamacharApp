package com.example.samacharapp.mydata

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInteface {

    @GET("top-headlines")
    fun getTopHeadlines(
        @Query("language") language: String,  // Default to US
        @Query("apiKey") apiKey: String = "923285118d68488bb294f702736565d5",  // API key
    ): Call<MyNewsdata>
}
