package com.example.newsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity()
class Article (
    //nullable aceito, pois alguns dados da api vem nulas, sendo elas bem aleatórias
    var source: Source,
    var author: String?,
    var title: String?,
    var description: String?,
    var url: String?,
    var urlToImage: String?,
    var publishedAt: Date?,
    var content: String?
): Serializable{
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null
    var imageCacheLocation: String? = ""
}