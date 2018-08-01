package com.example.kai.ganki.utils

import android.content.Context
import android.content.SharedPreferences


val sp: SharedPreferences by lazy { Common.context.getSharedPreferences("ganko", Context.MODE_PRIVATE) }

fun spSetString(key: String,value: String) = sp.edit().putString(key,value).apply()
fun spGetString(value: String) = sp.getString(value,"")

fun spSetBoolean(key: String,value: Boolean) = sp.edit().putBoolean(key,value).apply()
fun spGetBoolean(key: String) = sp.getBoolean(key,true)

fun spSetInt(key: String,value: Int) = sp.edit().putInt(key,value).apply()
fun spGetInt(key: String) = sp.getInt(key,0)