package com.invocatapp.todoapp.data

import javax.inject.Inject

class Repository @Inject constructor(localDataSource: LocalDataSource) {
    val localDataSource = localDataSource
}

