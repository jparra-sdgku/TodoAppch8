package com.example.todoappch8

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log

@SuppressLint("StaticFieldLeak")
object AppState {
    private var _appContext: Context? = null


    fun initialize(context: Context){
        _appContext = context.applicationContext   // stores the activity context directly

        Log.w("AppState","Safe context stored-- application context never leaks")
    }
}