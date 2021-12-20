package com.example.coroutine

import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import android.os.Bundle
import com.example.coroutine.databinding.ActivityMainBinding
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val download: Download by viewModels()
        binding.button1.setOnClickListener { download.downloadImage(URL("https://thispersondoesnotexist.com/image")) }
        download.bitmap.observe(this) {binding.imageView.setImageBitmap(it)}
    }
}