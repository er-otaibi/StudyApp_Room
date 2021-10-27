package com.example.studyapp_room

import androidx.room.*



@Dao
interface LessonDao{

    @Query("SELECT * FROM KotlinLesson")
    fun getAllMyLessons(): List<KotlinLesson>

    @Insert
    fun insertLesson(lesson: KotlinLesson)

    @Delete
    fun deleteOBJ(lesson: KotlinLesson)

    @Update
    fun updateOBJ(lesson: KotlinLesson)

}