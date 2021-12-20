package com.example.coroutines

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

class MainActivity : AppCompatActivity() {
    var secondsElapsed: Int = 0
    var active = true
    lateinit var textSecondsElapsed: TextView
    private lateinit var sharedPreferences: SharedPreferences


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        sharedPreferences = getSharedPreferences("sec", Context.MODE_PRIVATE)
        lifecycleScope.launchWhenResumed {
            while (isActive) {
                Log.d(ContentValues.TAG, "${Thread.currentThread()} running now")
                delay(1000)
                textSecondsElapsed.post { textSecondsElapsed.text = "Seconds Elapsed: ${secondsElapsed++}" }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        active = false
        with(sharedPreferences.edit()) {
            putInt("sec", secondsElapsed)
            apply()
        }
    }

    override fun onResume() {
        super.onResume()
        active = true
        secondsElapsed = sharedPreferences.getInt("sec", 0)
    }



}