package com.example.todo.add

import androidx.lifecycle.ViewModel
import com.example.todo.data.Todo
import com.example.todo.data.TodoRepository

import java.util.*

class AddViewModel(
    private val todoRepository: TodoRepository
) : ViewModel() {

    val todo = Todo(
        UUID.randomUUID().toString(),
        "",
        null,
        false,
        0
    )


    fun save(): String? {
        if (todo.title == "") return "Title is required"

        todo.created = System.currentTimeMillis()
        todoRepository.insert(todo)
        return null
    }

}