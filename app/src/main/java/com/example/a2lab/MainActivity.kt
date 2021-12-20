package com.example.a2lab

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast


class MainActivity : Activity() {
    val LOG_TAG = "myLogs"
    var cnt = 0

    /** Called when the activity is first created.  */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(LOG_TAG, "onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "onDestroy")
    }

    override fun onPause() {
        super.onPause()
        Log.d(LOG_TAG, "onPause")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(LOG_TAG, "onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(LOG_TAG, "onResume ")
    }


    override fun onStart() {
        super.onStart()
        Log.d(LOG_TAG, "onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.d(LOG_TAG, "onStop")
    }

    fun onclick(v: View?) {
        Toast.makeText(this, "Count = " + ++cnt, Toast.LENGTH_SHORT).show()
    }
}