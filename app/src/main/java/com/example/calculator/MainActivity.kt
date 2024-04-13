package com.example.calculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!
    private var operand1: Double = 0.0
    private var operator: String = ""
    private var isNewOperation: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        installSplashScreen()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            button0.setOnClickListener {
                appendDigit("0") }
            button1.setOnClickListener {
                appendDigit("1") }
            button2.setOnClickListener {
                appendDigit("2") }
            button3.setOnClickListener {
                appendDigit("3") }
            button4.setOnClickListener {
                appendDigit("4") }
            button5.setOnClickListener {
                appendDigit("5") }
            button6.setOnClickListener {
                appendDigit("6") }
            button7.setOnClickListener {
                appendDigit("7") }
            button8.setOnClickListener {
                appendDigit("8") }
            button9.setOnClickListener {
                appendDigit("9") }
            buttonDot.setOnClickListener {
                appendDot() }
            buttonAdd.setOnClickListener {
                setOperator("+") }
            buttonSubtract.setOnClickListener {
                setOperator("-") }
            buttonMultiply.setOnClickListener {
                setOperator("*") }
            buttonDivide.setOnClickListener {
                setOperator("/") }
            buttonEqual.setOnClickListener {
                calculateResult() }
            buttonClear.setOnClickListener {
                clear() }
        }
    }

    private fun appendDigit(digit: String) {
        if (isNewOperation) {
            binding.textViewResult.text = digit
            isNewOperation = false
        } else {
            binding.textViewResult.append(digit)
        }
    }


    private fun calculateResult() {
        val operand2 = binding.textViewResult.text.toString().toDouble()
        var result: Double = 0.0
        when (operator) {
            "+" -> result = operand1 + operand2
            "-" -> result = operand1 - operand2
            "*" -> result = operand1 * operand2
            "/" -> {
                if (operand2 != 0.0) {
                    result = operand1 / operand2
                } else {
                    binding.textViewResult.text = "Error"
                    return
                }
            }
        }
        binding.textViewResult.text = result.toString()

        isNewOperation = true
    }

    private fun setOperator(op: String) {
        operand1 = binding.textViewResult.text.toString().toDouble()
        operator = op
        isNewOperation = true
    }

    private fun clear() {
        binding.textViewResult.text = ""
        operand1 = 0.0
        operator = ""
        isNewOperation = true
    }

    private fun appendDot() {
        if (isNewOperation) {
            binding.textViewResult.text = "0."
            isNewOperation = false
        } else if (!binding.textViewResult.text.contains(".")) {
            binding.textViewResult.append(".")
        }
    }

}

