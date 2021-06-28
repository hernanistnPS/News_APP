package com.example.newsapp.Controller

import android.content.Context
import androidx.room.Room
import com.example.newsapp.AppDB.AppDataBase
import com.example.newsapp.model.PersonalNews

class PersonalNewsController(context: Context) {
    private val personalNewsController = Room.databaseBuilder(context, AppDataBase::class.java, "newsDB").allowMainThreadQueries().fallbackToDestructiveMigration().build().personalNewsDAO()

    fun getAll(): List<PersonalNews>{
        return personalNewsController.getALl()
    }
    fun insert(personalNews: PersonalNews){
        personalNewsController.insert(personalNews)
    }
    fun delete(personalNews: PersonalNews){
        personalNewsController.delete(personalNews)
    }
    fun update(personalNews: PersonalNews){
        personalNewsController.update(personalNews)
    }



}