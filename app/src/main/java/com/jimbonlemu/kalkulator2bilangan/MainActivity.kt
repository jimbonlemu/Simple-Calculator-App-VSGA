package com.jimbonlemu.kalkulator2bilangan

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class MainActivity : AppCompatActivity() {

    private var operator: String = ""
    private lateinit var firstNumberEditText: EditText
    private lateinit var secondNumberEditText: EditText
    private lateinit var viewOperasi: MaterialTextView
    private lateinit var resultText: MaterialTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Simple Calculator"

        firstNumberEditText = findViewById(R.id.firstNumber)
        secondNumberEditText = findViewById(R.id.secondNumber)
        viewOperasi = findViewById(R.id.viewOperasi)
        resultText = findViewById(R.id.resultText)

        setupOperatorButton(R.id.plusButton, "+")
        setupOperatorButton(R.id.minesButton, "-")
        setupOperatorButton(R.id.multipleButton, "*")
        setupOperatorButton(R.id.spareButton, "/")

        findViewById<MaterialButton>(R.id.resultButton).setOnClickListener { calculateResult() }
        findViewById<MaterialButton>(R.id.cleanButton).setOnClickListener { clearAllFields() }
    }

    private fun setupOperatorButton(buttonId: Int, operator: String) {
        findViewById<MaterialButton>(buttonId).setOnClickListener {
            this.operator = operator
            viewOperasi.text = operator
        }
    }

    private fun calculateResult() {
        val firstNumber = firstNumberEditText.text.toString().toDoubleOrNull()
        val secondNumber = secondNumberEditText.text.toString().toDoubleOrNull()

        if (firstNumber != null && secondNumber != null) {
            val result: Double? = when (operator) {
                "+" -> firstNumber + secondNumber
                "-" -> firstNumber - secondNumber
                "*" -> firstNumber * secondNumber
                "/" -> firstNumber / secondNumber
                else -> null
            }

            result?.let {
                resultText.text = when {
                    it.isInt() -> it.toInt().toString() // Hasil berupa integer
                    else -> it.toString() // Hasil berupa double
                }
            } ?: showToast("Operator tidak valid")
        } else {
            showToast("Input tidak valid")
        }
    }

    private fun Double.isInt() = this % 1 == 0.0


    private fun clearAllFields() {
        firstNumberEditText.text.clear()
        secondNumberEditText.text.clear()
        viewOperasi.text = ""
        resultText.text = ""
        operator = ""
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
