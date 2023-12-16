package com.invocatapp.todoapp.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
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

    fun insertToDo() {
        viewModelScope.launch {
            repository.localDataSource.insertToDo(ToDoModel(title = "Deneme", description =  "Deneme Açıklama", priority = Priority.HIGH, isChecked = true))
        }
    }

}




