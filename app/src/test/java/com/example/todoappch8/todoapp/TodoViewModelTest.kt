package com.example.todoappch8.todoapp

import com.example.todoappch8.TodoViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test

class TodoViewModelTest {
    // The system under test recreated before each test
    private lateinit var viewModel: TodoViewModel

    @Before
    fun setUp() {
        viewModel = TodoViewModel()
    }

    @Test
    fun addTask_withValidTitle_appearsInList() {
        // Arrange - the setUp() already created an empty ViewModel

        // Act-- call the function we want to test
        viewModel.addTask("Buy Groceries")

        // Assert - check the result
        assertEquals(1, viewModel.getTaskCount())
        assertTrue(viewModel.containsTask("Buy Groceries"))
    }

    // Test addTask with Blank Title
    @Test
    fun addTask_withBlankTitle_isIgnore() {
        viewModel.addTask("      ")
        assertEquals(0, viewModel.getTaskCount())
    }

    // Test addTask with EmptyString
    @Test
    fun addTask_withEmptyString_isIgnored() {
        viewModel.addTask("")
        assertEquals(0, viewModel.getTaskCount())
    }

    // Test for Title is Trimmed
    @Test
    fun addTask_titleIsTrimmed() {
        // Leading and trailing spaces should be removed
        viewModel.addTask("  Buy Groceries  ")
        assertTrue(viewModel.containsTask("Buy Groceries"))
    }

    @Test
    fun removeTask_withValidId_removesTask() {
        //Arrange -- add a task to the list so we can remove it
        viewModel.addTask("Buy Groceries")
        val taskId = viewModel.tasks[0].id

        //Act -- call the function we want to test
        viewModel.removeTask(taskId)

        // Assert -- check the result
        assertEquals(0, viewModel.getTaskCount())
    }

    // challenge  removing a non-existing task id should not crash the app or affect the list
    @Test
    fun removeTask_withInvalidId_doesNothing() {
        // Removing a non-existent id should not crash or affect the list
        viewModel.addTask("Buy Groceries")
        viewModel.removeTask(999)  // id 999 does not exist
        assertEquals(1, viewModel.getTaskCount())
    }


}