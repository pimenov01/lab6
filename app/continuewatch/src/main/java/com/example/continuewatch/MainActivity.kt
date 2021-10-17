package com.example.continuewatch

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var secondsElapsed: Int = 0
    var active = true
    lateinit var textSecondsElapsed: TextView
    private lateinit var sharedPreferences: SharedPreferences //remove for 1-st option

    @SuppressLint("SetTextI18n")
    var backgroundThread = Thread {
        while (true) {
            Thread.sleep(1000)
            textSecondsElapsed.post {
                if (active) {
                textSecondsElapsed.setText("Seconds elapsed: " + secondsElapsed++)
                }
            }
        }
    }

    /**
     * @param sharedPreferences is optional for 2-nd variation
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        backgroundThread.start()
        sharedPreferences = getSharedPreferences("sec", Context.MODE_PRIVATE)
    }
    /**
     * 1-st option via onSaveInstanceState()/onRestoreInstanceState
     */
    /*override fun onSaveInstanceState(outState: Bundle) {
        outState.run { putInt("sec", secondsElapsed) }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.run { secondsElapsed = getInt("sec") }
    }

    override fun onPause() {
        super.onPause()
        active = false
    }

    override fun onResume() {
        super.onResume()
        active = true
    }*/

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

}