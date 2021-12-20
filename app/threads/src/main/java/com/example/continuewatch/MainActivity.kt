package com.example.continuewatch

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var secondsElapsed: Int = 0
    var active = true
    lateinit var textSecondsElapsed: TextView
    private lateinit var backgroundThread: Thread
    private lateinit var sharedPreferences: SharedPreferences //remove for 1-st option

    @SuppressLint("SetTextI18n")
    fun createThread() = Thread {
        try {
            while(!Thread.currentThread().isInterrupted) {
                Log.d(TAG, "${Thread.currentThread()} running now")
                Thread.sleep(1000)
                textSecondsElapsed.post { textSecondsElapsed.text = "Seconds Elapsed: ${secondsElapsed++}" }
            }
        }
        catch (e: InterruptedException) {
            Log.d(TAG, "${Thread.currentThread()} caught exception")
        }
    }

    /**
     * @param sharedPreferences is optional for 2-nd variation
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        sharedPreferences = getSharedPreferences("sec", Context.MODE_PRIVATE)
    }

    /**
     * 2-nd option via sharedPreferences
     */

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

    override fun onStart() {
        super.onStart()
        backgroundThread = createThread()
        backgroundThread.start()
    }

    override fun onStop() {
        super.onStop()
        backgroundThread.interrupt()
    }

}