package com.invocatapp.todoapp.data

import com.invocatapp.todoapp.model.ToDoModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val toDoDao: ToDoDao
) {
    fun getAllToDo(): Flow<List<ToDoModel>> {
        return toDoDao.getAllToDo()
    }

    fun searchToDo(searchQuery:String):Flow<List<ToDoModel>> {
        return toDoDao.searchToDo(searchQuery)
    }

    suspend fun insertToDo(toDoModel: ToDoModel) {
        toDoDao.insertToDo(toDoModel)
    }

    suspend fun updateToDo(toDoModel: ToDoModel) {
        toDoDao.updateToDo(toDoModel)
    }

    suspend fun getToDo(toDoId:Int) {
        toDoDao.getToDo(toDoId)
    }

    suspend fun deleteToDo(toDoModel: ToDoModel) {
        toDoDao.deleteToDo(toDoModel)
    }
}




