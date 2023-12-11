package com.invocatapp.todoapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.invocatapp.todoapp.model.ToDoModel

@Database(entities = [ToDoModel::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun toDoDao():ToDoDao
}




