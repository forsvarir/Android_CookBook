package com.forsarir.development.cookbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import android.widget.TextView

class GestureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gesture)

        val homeButton: Button = findViewById(R.id.buttonHome)

        homeButton.setOnClickListener {
            val homeIntent = Intent(this, MainActivity::class.java).apply {}
            startActivity(homeIntent)
        }
    }

    private var action = ""
    private var lastX : Int = 0
    private var lastY : Int = 0

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var command = ""
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                command = "DOWN"
                action = ""
                lastX = 0
                lastY = 0
            }
            MotionEvent.ACTION_MOVE -> {
                command = "MOVING"
            }
            MotionEvent.ACTION_UP -> {
                command = "UP"
            }
        }

        val currentPosX : Int = (event!!.x / 10).toInt()
        val currentPosY : Int = (event.y / 10).toInt()

        if(currentPosX != lastX || currentPosY != lastY) {
            lastX = currentPosX
            lastY = currentPosY
            action += "($lastX,$lastY)"
        }

        val text = "$command $action"

        val statusView = findViewById<TextView>(R.id.gestureDetails)
        statusView?.text = text
        return super.onTouchEvent(event)
    }
}