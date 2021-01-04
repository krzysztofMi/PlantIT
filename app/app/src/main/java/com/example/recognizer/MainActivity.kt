package com.example.recognizer

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.recognizer.ml.PlantClassifier


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prepareButton()
    }

    private fun prepareButton() {
        findViewById<Button>(R.id.recognize_bttn).setOnClickListener{
            startActivity(Intent(this, RecognizeActivity::class.java))
        }
        findViewById<Button>(R.id.search_bttn).setOnClickListener{
            startActivity(Intent(this, SearchActivity::class.java))
        }
    }
}
