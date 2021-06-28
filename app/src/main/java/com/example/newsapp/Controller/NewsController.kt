package com.example.newsapp.Controller

import com.example.newsapp.model.News
import com.example.newsapp.repository.NewsRepository

class NewsController() {

    fun getAllFromAPI(typeArticle: String, page: String, apikey: String): News {
            val newsFromAPI = NewsRepository().getNews(typeArticle, "2021-25-06", page, apikey)
            newsFromAPI!!.articles = newsFromAPI.articles.sortedByDescending { it.publishedAt }
            return newsFromAPI
    }
}