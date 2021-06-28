package com.example.newsapp.model

import androidx.room.PrimaryKey
import java.io.Serializable

class News(
    var status:String,
    var totalResults: Int,
    var articles: List<Article>
) : Serializable{
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}