package com.example.coroutine

import android.content.ContentValues.TAG
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Download: ViewModel() {
    val bitmap: MutableLiveData<Bitmap> = MutableLiveData()

    fun downloadImage(url: URL) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG, "Downloading in ${Thread.currentThread()}")
            bitmap.postValue(BitmapFactory.decodeStream(url.openConnection().getInputStream()))
        }
    }

}