package com.example.newsapp.AppDB

import androidx.room.TypeConverter
import com.example.newsapp.model.Article
import com.example.newsapp.model.Source
import com.google.gson.Gson
import java.util.*


class Converters {

    @TypeConverter
    fun dateToString(value: Date) = Gson().toJson(value)

    @TypeConverter
    fun stringToDate(string: String) = Gson().fromJson(string, Date::class.java)

    @TypeConverter
    fun sourceToJson(value: Source) = Gson().toJson(value)

    @TypeConverter
    fun jsonToSource(string: String) = Gson().fromJson(string, Source::class.java)

    @TypeConverter
    fun listToJson(value: List<Article>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Article>::class.java).toList()
}