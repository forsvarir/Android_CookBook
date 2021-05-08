package com.forsarir.development.cookbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val photoButton: Button = findViewById(R.id.buttonPhoto)

        photoButton.setOnClickListener {
            val photoActivityIntent = Intent(this, PhotoActivity::class.java).apply {}
            startActivity(photoActivityIntent)
        }
    }
}