package com.example.studyapp_room

import androidx.room.*

@Dao
interface AndroidDoa{

    @Query("SELECT * FROM AndroidLesson")
    fun getAllAndroid(): List<AndroidLesson>

    @Insert
    fun insertLesson(lesson: AndroidLesson)

    @Update
    fun updateAndroid(lesson: AndroidLesson)

    @Delete
    fun deleteAndroid(lesson: AndroidLesson)


}