package com.example.simplecalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var angkaPertama: EditText
    private lateinit var angkaKedua: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var resultButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        angkaPertama = findViewById(R.id.angkaPertama)
        angkaKedua = findViewById(R.id.angkaKedua)
        radioGroup = findViewById(R.id.radioGroup)
        resultButton = findViewById(R.id.result)

        // Set the click listener for the button
        resultButton.setOnClickListener {
            // Get the input numbers
            val firstNumberStr = angkaPertama.text.toString()
            val secondNumberStr = angkaKedua.text.toString()

            // Validate inputs
            if (firstNumberStr.isEmpty() || secondNumberStr.isEmpty()) {
                Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Convert strings to double
            val firstNumber = firstNumberStr.toDouble()
            val secondNumber = secondNumberStr.toDouble()

            // Check which radio button is selected
            val selectedOperation = radioGroup.checkedRadioButtonId

            if (selectedOperation == -1) {
                Toast.makeText(this, "Please select an operation", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var result = 0.0

            // Perform the selected operation
            val selectedRadioButton: RadioButton = findViewById(selectedOperation)
            val operation = selectedRadioButton.text.toString()

            when (operation) {
                "+" -> result = firstNumber + secondNumber
                "-" -> result = firstNumber - secondNumber
                "*" -> result = firstNumber * secondNumber
                ":" -> {
                    if (secondNumber != 0.0) {
                        result = firstNumber / secondNumber
                    } else {
                        Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                }
            }

            // Start ResultActivity and pass the result
            val intent = Intent(this, Result::class.java)
            intent.putExtra("result", result)
            startActivity(intent)
        }
    }
}