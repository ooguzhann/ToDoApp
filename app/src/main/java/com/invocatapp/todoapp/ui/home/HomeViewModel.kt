package com.invocatapp.todoapp.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.invocatapp.todoapp.data.Repository
import com.invocatapp.todoapp.model.Priority
import com.invocatapp.todoapp.model.ToDoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application, private val repository: Repository
):AndroidViewModel(application) {

    val toDoList = repository.localDataSource.getAllToDo().asLiveData() // ekleme güncelleme işlemlerinde bu kod sürekli tetiklencek
    var searchToDoList:LiveData<List<ToDoModel>> = MutableLiveData()
    val searchQuery = MutableLiveData("")

    fun updateToDo(toDoModel: ToDoModel) {
        val updatedToDoModel = toDoModel.copy(isChecked = toDoModel.isChecked?.not())
        viewModelScope.launch{
            repository.localDataSource.updateToDo(updatedToDoModel)
        }
    }

    fun searchToDo(searchQuery:String) {
        searchToDoList = repository.localDataSource.searchToDo("%$searchQuery%").asLiveData()
        this.searchQuery.value = searchQuery
    }


}




