package com.example.executor

import android.content.ContentValues.TAG
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Download: ViewModel() {
    private val pool : ExecutorService = Executors.newSingleThreadExecutor()
    val bitmap: MutableLiveData<Bitmap> = MutableLiveData()

    fun downloadImage(url: URL) {
        pool.execute {
            Log.d(TAG, "Downloading in ${Thread.currentThread()}")
            bitmap.postValue(BitmapFactory.decodeStream(url.openConnection().getInputStream()))
        }
    }

}