package com.forsarir.development.cookbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import com.forsarir.development.cookbook.ui.theme.CookBookTheme

class MainCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CookBookTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    Greeting("Android")
                    ClickableSample()
                }
            }
        }
    }
}


@Composable
fun ClickableSample() {
    val dragStart = remember { mutableStateOf("") }
    val dragEnd = remember { mutableStateOf("") }
    val outputMessage = remember { mutableStateOf("") }

    var posX by remember { mutableStateOf(0f) }
    var posY by remember { mutableStateOf(0f) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    val points = mutableListOf<String>()

    Box(modifier = Modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            detectDragGestures(
                onDragStart = {
                    dragStart.value = "FROM:(${it.x},${it.y})"
                    dragEnd.value = ""
                    posX = it.x
                    posY = it.y

                    points.clear()
                    outputMessage.value = "${dragStart.value},${dragEnd.value}"
                },
                onDrag = { _, offset ->
                    posX += offset.x
                    posY += offset.y
                    offsetX += offset.x
                    offsetY += offset.y
                    points.add("($posX, $posY)")
                },
                onDragEnd = {
                    dragEnd.value = "TO:(${posX},${posY})"

                    val pointsMessage = points.reduce { acc, item ->
                        "$acc,$item"
                    }

                    outputMessage.value = "${dragStart.value},${pointsMessage},${dragEnd.value}"
                }
            )
        }
    ) {
        Text(
            text = outputMessage.value
        )

    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CookBookTheme {
        Greeting("Android")
    }
}