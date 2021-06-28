package com.example.newsapp.DAO

import androidx.room.*
import com.example.newsapp.model.Article

@Dao
interface ArticleDAO {
    @Query("Select * from Article")
    fun getALl(): List<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg article: Article)

    @Delete
    fun delete(vararg article: Article)

    @Update
    fun update(vararg article: Article)
}