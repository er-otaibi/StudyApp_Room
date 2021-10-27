package com.example.studyapp_room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "AndroidLesson")
data class AndroidLesson(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id : Int = 0,
    @ColumnInfo(name = "Title") val title: String,
    @ColumnInfo(name = "Description") val description: String,
    @ColumnInfo(name = "Details") val details: String)