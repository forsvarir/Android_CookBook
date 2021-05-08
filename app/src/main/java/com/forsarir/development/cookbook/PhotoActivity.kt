package com.forsarir.development.cookbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PhotoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        val homeButton: Button = findViewById(R.id.buttonHome)

        homeButton.setOnClickListener {
            val homeIntent = Intent(this, MainActivity::class.java).apply {}
            startActivity(homeIntent)
        }
    }
}