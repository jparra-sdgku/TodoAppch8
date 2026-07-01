package com.example.todoappch8

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TodoViewModel : ViewModel() {
    // _tasks is private — only this class can modify it
    // This is a mutable list that holds all current tasks

    private val _tasks = mutableStateListOf<Task>()

    // tasks is public — the UI reads from this list but cannot modify it directly
    val tasks: List<Task> get() = _tasks

    //Counter used to generate a unique ID for each new task
    private var _nextId = 1

    // challenge create the function addTask() here
    //Adds a new Task to the list
    // Does nothing if the title is blank (empty or only whitespace)
    fun addTask(title: String) {
        if (title.isNotBlank()) {
                _tasks.add(Task(id = _nextId++, title = title.trim()))
            }
    }

    //Removes a task from the list by its Id
    fun removeTask(taskId: Int) {
        _tasks.removeAll { it.id == taskId }
    }

    //Returns the number of tasks in the list
    //Used in Unit test to verify task were added correctly
    fun getTaskCount(): Int = _tasks.size

    // Returns true if a task with the given title exist in the list
    //Used in unittest to verify the correct task was added

    fun containsTask(title: String): Boolean {
        return _tasks.any { it.title == title }
    }

    //This simulates a slow operation running on the main Thread
    private fun simulatesSlowOperation() {
        Thread.sleep(5000)
    }
}











