package com.example.newsapp.Controller

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import com.example.newsapp.AppDB.AppDataBase
import com.example.newsapp.model.Article
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArticleController(val context: Context) {
    private val articleController =
        Room.databaseBuilder(context, AppDataBase::class.java, "newsDB").allowMainThreadQueries()
            .fallbackToDestructiveMigration().build().articleDAO()

    fun getAllFromDB(): List<Article> {
        return articleController.getALl().sortedByDescending { it.publishedAt }
    }

    fun insert(article: Article) {
        articleController.insert(article)
    }

    fun delete(article: Article) {
        articleController.delete(article)
    }

    fun update(article: Article) {
        articleController.update(article)
    }

    fun getAll(typeArticle: String, page: String, apikey: String): List<Article> {
        val allfromDB = getAllFromDB()
        val alldbSortedByOldPublished = allfromDB.sortedBy { it.publishedAt }
        val sizeDB = allfromDB.size
        if (sizeDB > 20) {
            //se caso houver mais de 20 noticias, automaticamente deleta 10 noticias mais antigas
            for (i in 0..9) {
                delete(alldbSortedByOldPublished.get(i))
            }
        }
        try {
            val newsFromAPI = NewsController().getAllFromAPI(typeArticle, page, apikey)
            val listURL = arrayListOf<String>()
            allfromDB.forEach {
                listURL.add(it.url?:"")
            }
            newsFromAPI!!.articles.forEach {
                if (!listURL.contains(it.url)) {
                    insert(it)
                }
            }
            return newsFromAPI.articles
        } catch (e: Exception) {
            Log.e("ERROR GET API: ", e.toString())
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(
                    context,
                    "Não foi possível se conectar a internet!",
                    Toast.LENGTH_LONG
                )
                    .show()
            }
            return allfromDB
        }
    }

}