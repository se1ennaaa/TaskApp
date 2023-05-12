package com.example.appleadd.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appleadd.model.Task
import com.example.taskapp.data.db.TaskDao

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}