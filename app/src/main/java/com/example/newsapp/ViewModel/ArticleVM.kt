package com.example.newsapp.ViewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.Controller.ArticleController
import com.example.newsapp.model.Article
import com.example.newsapp.model.News
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ArticleVM(val context: Context, val apikey:String) : ViewModel() {
    private val controller = ArticleController(context)
    private var newsLiveData: MutableLiveData<List<Article>> = MutableLiveData()

    fun update(news: Article) {
        controller.update(news)
    }

    fun delete(news: Article) {
        controller.delete(news)
    }

    fun insert(news: Article) {
        controller.insert(news)
    }

    fun getAll(
        typeArticle: String,
        page: String
    ): MutableLiveData<List<Article>> {
        CoroutineScope(Dispatchers.IO).launch {
            async {
                newsLiveData.postValue(controller.getAll(typeArticle, page, apikey))
            }
        }
        return newsLiveData
    }



}