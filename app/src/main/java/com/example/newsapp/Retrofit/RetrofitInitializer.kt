package com.example.newsapp.Retrofit

import com.example.newsapp.Retrofit.Services.NewsService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {
    private val retrofit = Retrofit.Builder().baseUrl("https://newsapi.org")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun newsService() = retrofit.create(NewsService::class.java)
}