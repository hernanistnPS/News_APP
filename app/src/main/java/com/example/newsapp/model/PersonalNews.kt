package com.example.newsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
@Entity
class PersonalNews(
    var theme: String) : Serializable{
        @PrimaryKey(autoGenerate = true)
        var id: Int? = null
}