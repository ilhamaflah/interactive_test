package com.example.interactivetest.controllers

import android.content.Context

class localStorage(username: String, konteks: Context) {
    val sharedPref = konteks.getSharedPreferences("interactive", Context.MODE_PRIVATE)
    val editor = sharedPref.edit()
    val USERNAME = sharedPref.getString("USER", username)

    fun editLocalUser(username: String){
        editor.putString("USER", username)
        editor.apply()
    }
}