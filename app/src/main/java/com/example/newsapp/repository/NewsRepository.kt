package com.example.newsapp.repository

import android.util.Log
import com.example.newsapp.Retrofit.RetrofitInitializer
import com.example.newsapp.Retrofit.Services.NewsService
import com.example.newsapp.model.News
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Response

class NewsRepository {
    fun getNews(typeArticle: String, dateFrom: String, page: String, apikey: String ): News? {
        val call = RetrofitInitializer().newsService().getNewsPageSize10(typeArticle, dateFrom, "pt", "10",page, apikey, "publishedAt")
        Log.e("URL API: ", call.request().url().toString())
        val body = call.execute().body()
        return body
    }
}