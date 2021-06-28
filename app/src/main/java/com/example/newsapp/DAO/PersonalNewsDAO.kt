package com.example.newsapp.DAO

import androidx.room.*
import com.example.newsapp.model.PersonalNews
@Dao
interface PersonalNewsDAO {
    @Query("Select * from PersonalNews")
    fun getALl(): List<PersonalNews>

    @Insert
    fun insert(vararg personalNews: PersonalNews)

    @Delete
    fun delete(vararg personalNews: PersonalNews)

    @Update
    fun update(vararg personalNews: PersonalNews)

}