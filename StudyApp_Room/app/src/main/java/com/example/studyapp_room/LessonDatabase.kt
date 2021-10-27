package com.example.studyapp_room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(entities = [KotlinLesson::class, AndroidLesson::class], version = 2,exportSchema = false)
abstract class LessonDatabase: RoomDatabase() {

    companion object{
        var instance:LessonDatabase?=null;
        fun getInstance(ctx: Context):LessonDatabase
        {
            if(instance!=null)
            {
                return  instance as LessonDatabase;
            }
            instance= Room.databaseBuilder(ctx,LessonDatabase::class.java,"Lessons").run { allowMainThreadQueries() }.fallbackToDestructiveMigration().build();
            return instance as LessonDatabase;
        }
    }
    abstract fun LessonDao():LessonDao;
    abstract fun AndroidDoa():AndroidDoa;
}