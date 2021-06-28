package com.example.newsapp.Retrofit.Services

import com.example.newsapp.model.News
import retrofit2.Call
import retrofit2.http.*

interface NewsService {

    @GET("/v2/everything")
    fun getNewsPageSize10(
        @Query("q") typeArticle: String,
        @Query("from") dateFrom: String,
        @Query("language") language: String,
        @Query("pageSize") pageSize: String,
        @Query("page") page: String,
        @Query("apiKey") apiKey: String,
        @Query("sortBy") sortBy: String
    ): Call<News>

}