package com.example.todo.add

import com.example.todo.data.TodoRepository
import junit.framework.Assert.*
import org.junit.Test
import org.mockito.kotlin.*


class AddViewModelTest{

    @Test
    fun test_save(){
        val repository:TodoRepository = mock()
        val model =AddViewModel(repository)
        model.todo.title = "Test todo"

        val actual = model.save()
        assertNull(actual)
        verify(repository).insert(any())

    }

    @Test
    fun test_saveNoTitle(){
        val repository:TodoRepository = mock()
        val model =AddViewModel(repository)
        val expected = "Title is required"

        val actual = model.save()
        assertEquals(expected,actual)
        verify(repository, never()).insert(any())

    }


    @Test
    fun test_saveWithData(){
        val repository:TodoRepository = mock()
        val model =AddViewModel(repository)
        val actualTitle = "Test Todo"
        model.todo.title = actualTitle

        val actualDate = System.currentTimeMillis()
        model.todo.dueDate = actualDate

        val actual = model.save()
        assertNull(actual)
        verify(repository).insert(
                argThat {
                    title == actualTitle && dueDate == actualDate
                }
        )

    }

    @Test
    fun test_saveWithoutData(){
        val repository:TodoRepository = mock()
        val model =AddViewModel(repository)
        val actualTitle = "Test Todo"
        model.todo.title = actualTitle

        val actual = model.save()
        assertNull(actual)
        verify(repository).insert(
                argThat {
                    title == actualTitle && dueDate == null
                }
        )

    }



}