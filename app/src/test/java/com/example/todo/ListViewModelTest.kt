package com.example.todo


import androidx.lifecycle.MutableLiveData
import com.example.todo.data.Todo
import com.example.todo.data.TodoRepository
import com.example.todo.list.ListViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


class ListViewModelTest {


    @get:Rule
    val exceptionRule = ExpectedException.none()
    val now = System.currentTimeMillis()
    val day = 1000 * 60 * 60 * 24

    @Test
    fun test_allTodosEmpty() {
        val expected =  1
        val repository :TodoRepository = mock()
        whenever(repository.getAllTodos())
            .thenReturn(MutableLiveData(arrayListOf(
                 Todo("5", "Todo 5", now - day, false, now)
            )))
        val model = ListViewModel(repository)

        val todos = model.allTodos.value

        assertNotNull(todos)
        assertEquals(expected, todos!!.size)
    }

    @Test
    fun test_allTodosMultiple() {
        val expected =  3
        val repository :TodoRepository = mock()
        whenever(repository.getAllTodos())
            .thenReturn(MutableLiveData(arrayListOf(
                Todo("5", "Todo 5", now - day, false, now),
                        Todo("3", "Todo 3", now + day, false, now),
                Todo("4", "Todo 4", now + day, false, now)
            )))
        val model = ListViewModel(repository)

        val todos = model.allTodos.value

        assertNotNull(todos)
        assertEquals(expected, todos!!.size)
    }

    @Test
    fun test_allTodosSingle() {
        val expected = 1
        val repository :TodoRepository = mock()
        whenever(repository.getAllTodos())
            .thenReturn(MutableLiveData(arrayListOf(
                Todo("5", "Todo 5", now - day, false, now)
            )))
        val model = ListViewModel(repository)

        val todos = model.allTodos.value

        assertNotNull(todos)
        assertEquals(expected, todos!!.size)
    }

    @Test
    fun test_upcomingTodosCountEmpty() {
        val repository :TodoRepository = mock()
        val expected = 0
        whenever(repository.getUpcomingTodosCount()).thenReturn(MutableLiveData(expected))
        val model = ListViewModel(repository)

        val todoCount = model.upcomingTodosCount?.value

        assertNotNull(todoCount)
        assertEquals(expected, todoCount)
    }

    @Test
    fun test_ToggleTodo() {
        val repository:TodoRepository = mock()

        val id = "fake"
        val model = ListViewModel(repository)


        model.toggleTodo(id)
        verify(repository).toggleTodo(id)
    }


    @Test
    fun test_ToggleTodoNotFound() {
        val repository:TodoRepository = mock()
       val exceptionMessage = "Todo not found"
        val id = "fake"
        whenever(repository.toggleTodo(id)).thenThrow(IllegalArgumentException(exceptionMessage))

        val model = ListViewModel(repository)
        exceptionRule.expect(IllegalArgumentException::class.java)
        exceptionRule.expectMessage(exceptionMessage)
        model.toggleTodo(id)
        verify(repository).toggleTodo(id)
    }


}
