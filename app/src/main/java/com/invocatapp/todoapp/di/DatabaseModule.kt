package com.invocatapp.todoapp.di

import android.content.Context
import androidx.room.Room
import com.invocatapp.todoapp.data.AppDatabase
import com.invocatapp.todoapp.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context:Context) = Room.databaseBuilder( // Database oluşturuldu
        context, AppDatabase::class.java, DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideToDoDao(database: AppDatabase) = database.toDoDao()
}