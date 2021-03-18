package com.example.todo

import android.app.Application
import com.example.todo.data.TodoRepository
import com.example.todo.data.TodoRoomDatabase
import com.example.todo.data.TodoRoomRepository

class TodoApplication : Application() {

    val todoRepository: TodoRepository
        get() = TodoRoomRepository(TodoRoomDatabase.getInstance(this.applicationContext)!!.todoDao())
}