package com.example.todoappch8.todoapp

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.todoappch8.TodoViewModel
import com.example.todoappch8.ui.screens.TodoScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TodoScreenTest {
    // This rule launches the composable in a test environment

    @get:Rule
    val composeTestRule = createComposeRule()

    // A fresh ViewModel for each test
    private lateinit var _viewModel: TodoViewModel

    @Before
    fun setUp() {
        _viewModel = TodoViewModel()

        // Set up the composable with the ViewModel -- same as MainActivity does
        composeTestRule.setContent {
            TodoScreen(viewModel = _viewModel)
        }
    }

    @Test
    fun addTask_userTypesAndClicksAdd_taskAppearsInList() {
        //Arrange -- the setUp() already launched TodoScreen with a fresh ViewModel

        //Verify the task does not exist yet
        composeTestRule.onNodeWithText("Buy Groceries").assertDoesNotExist()

        //Act -- find the input field by its testTag and type text
        composeTestRule
            .onNodeWithTag("input_field")
            .performTextInput("Buy Groceries")
        // Act -- find the Add button by its testTag and click it
        composeTestRule
            .onNodeWithTag("add_button")
            .performClick()

        // Assert -- verify the task was added
        //composeTestRule.onNodeWithText("Buy Groceries").assertExists()
        composeTestRule
            .onNodeWithText("Buy Groceries")
            .assertIsDisplayed()
    }

    @Test
    fun addTask_inputField_ClearsAfterAdding() {
        composeTestRule
            .onNodeWithTag("input_field")
            .performTextInput("Do Homework")

        composeTestRule
            .onNodeWithTag("add_button")
            .performClick()

        //The input field should be empty after adding a task
        composeTestRule
            .onNodeWithTag("input_field")
            .assert(hasText(""))
    }

    // Task counter test : The task counter should reflect the number of tasks in the list after adding a task
    @Test
    fun taskCounter_updates_afterAddingTask() {
        composeTestRule
            .onNodeWithText("0 task(s)")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("input_field")
            .performTextInput("Buy Groceries")

        composeTestRule
            .onNodeWithTag("add_button")
            .performClick()

        composeTestRule
            .onNodeWithText("1 task(s)")
            .assertIsDisplayed()
    }
}















