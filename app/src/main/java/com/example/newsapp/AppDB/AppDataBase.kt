package com.example.newsapp.AppDB

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.DAO.ArticleDAO
import com.example.newsapp.DAO.PersonalNewsDAO
import com.example.newsapp.model.Article
import com.example.newsapp.model.PersonalNews


@Database(version = 1, entities = arrayOf (Article::class, PersonalNews::class), exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDataBase: RoomDatabase() {
    abstract fun articleDAO(): ArticleDAO
    abstract fun personalNewsDAO(): PersonalNewsDAO
}