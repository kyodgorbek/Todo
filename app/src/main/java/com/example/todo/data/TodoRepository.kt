package com.example.todo.data

import androidx.lifecycle.LiveData
import com.example.todo.data.Todo

interface TodoRepository {

    fun getAllTodos(): LiveData<List<Todo>>

    fun insert(todo: Todo)

    fun toggleTodo(id: String)

    fun getUpcomingTodosCount(): LiveData<Int>
}