package com.example.simplecalculator

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Result : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)

        val result = intent.getDoubleExtra("result", 0.0)

        // Display the result in the TextView
        val resultTextView: TextView = findViewById(R.id.resultTextView)
        resultTextView.text = "Hasil: $result"
    }
}