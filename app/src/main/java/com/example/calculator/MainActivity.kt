package com.example.calculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculator.databinding.ActivityMainBinding
import java.util.Stack

class MainActivity : AppCompatActivity() {
//    connect to activity_main xml by lazy
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    fun precedence(op: Char): Int {
        return when (op) {
            '+', '-' -> 1
            '*', '/' -> 2
            else -> -1
        }
    }

    fun applyOp(a: Int, b: Int, op: Char): Int {
        return when (op) {
            '+' -> a + b
            '-' -> a - b
            '*' -> a * b
            '/' -> a / b
            else -> 0
        }
    }

    fun evaluate(expression: String): Int {
        val values = Stack<Int>()
        val ops = Stack<Char>()
        var i = 0
        while (i < expression.length) {
            if (expression[i].isDigit()) {
                var buffer = StringBuilder()
                while (i < expression.length && expression[i].isDigit()) {
                    buffer.append(expression[i++])
                }
                values.push(buffer.toString().toInt())
                i--
            } else if (expression[i] == '(') {
                ops.push(expression[i])
            } else if (expression[i] == ')') {
                while (ops.peek() != '(') {
                    values.push(applyOp(values.pop(), values.pop(), ops.pop()))
                }
                ops.pop()
            } else if (expression[i] in "+-*/") {
                while (ops.isNotEmpty() && precedence(ops.peek()) >= precedence(expression[i])) {
                    values.push(applyOp(values.pop(), values.pop(), ops.pop()))
                }
                ops.push(expression[i])
            }
            i++
        }
        while (ops.isNotEmpty()) {
            values.push(applyOp(values.pop(), values.pop(), ops.pop()))
        }
        return values.pop()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//        delete button
        binding.del.setOnClickListener {
            val takeCalc = binding.calc.text
            val calcLength = takeCalc.length
            if (calcLength == 1) {
                if (takeCalc == "0") {}
                else binding.calc.text = "0"
            } else {
                binding.calc.text = takeCalc.substring(0, calcLength-1)
            }
        }
        binding.zero.setOnClickListener {
            val takeCalc = binding.calc.text.toString()
            if (takeCalc == "0") {}
            else binding.calc.text = takeCalc + '0'
        }
        binding.one.setOnClickListener {
            val takeCalc = binding.calc.text.toString()
            if (takeCalc == "0") { binding.calc.text = "1" }
            else binding.calc.text = takeCalc + '1'
        }
        binding.two.setOnClickListener {
            val takeCalc = binding.calc.text.toString()
            if (takeCalc == "0") { binding.calc.text = "2" }
            else binding.calc.text = takeCalc + '2'
        }
        binding.three.setOnClickListener {
            val takeCalc = binding.calc.text.toString()
            if (takeCalc == "0") { binding.calc.text = "3" }
            else binding.calc.text = takeCalc + '3'
        }
        binding.four.setOnClickListener {
            val takeCalc = binding.calc.text.toString()
            if (takeCalc == "0") { binding.calc.text = "4" }
            else binding.calc.text = takeCalc + '4'
        }
        binding.five.setOnClickListener {
            val takeCalc = binding.calc.text.toString()
            if (takeCalc == "0") { binding.calc.text = "5" }
            else binding.calc.text = takeCalc + '5'
        }
        binding.six.setOnClickListener {
            val takeCalc = binding.calc.text.toString()
            if (takeCalc == "0") { binding.calc.text = "6" }
            else binding.calc.text = takeCalc + '6'
        }
        binding.seven.setOnClickListener {
            val takeCalc = binding.calc.text.toString()
            if (takeCalc == "0") { binding.calc.text = "7" }
            else binding.calc.text = takeCalc + '7'
        }
        binding.eight.setOnClickListener {
            val takeCalc = binding.calc.text.toString()
            if (takeCalc == "0") { binding.calc.text = "8" }
            else binding.calc.text = takeCalc + '8'
        }
        binding.nine.setOnClickListener {
            val takeCalc = binding.calc.text.toString()
            if (takeCalc == "0") { binding.calc.text = "9" }
            else binding.calc.text = takeCalc + '9'
        }
        binding.clear.setOnClickListener {
            val takeCalc = binding.calc.text.toString()
            if (takeCalc == "0") {
                binding.res.text = "0"
            } else binding.calc.text = "0"
        }
        binding.plus.setOnClickListener{
            val takeCalc = binding.calc.text.toString()
            val calcLength = takeCalc.length
            val lastElement = takeCalc.last()
            if (lastElement in "+-*/") {
                binding.calc.text = takeCalc.substring(0, calcLength-1) + '+'
            } else binding.calc.text = takeCalc + '+'
        }
        binding.min.setOnClickListener {
            val takeCalc = binding.calc.text.toString()
            val calcLength = takeCalc.length
            val lastElement = takeCalc.last()
            if (lastElement in "+-*/") {
                binding.calc.text = takeCalc.substring(0, calcLength-1) + '-'
            } else binding.calc.text = takeCalc + '+'
        }
        binding.multiply.setOnClickListener {
            val takeCalc = binding.calc.text.toString()
            val calcLength = takeCalc.length
            val lastElement = takeCalc.last()
            if (lastElement in "+-*/") {
                binding.calc.text = takeCalc.substring(0, calcLength-1) + '*'
            } else binding.calc.text = takeCalc + '*'
        }
        binding.divide.setOnClickListener {
            val takeCalc = binding.calc.text.toString()
            val calcLength = takeCalc.length
            val lastElement = takeCalc.last()
            if (lastElement in "+-*/") {
                binding.calc.text = takeCalc.substring(0, calcLength-1) + '/'
            } else binding.calc.text = takeCalc + '/'
        }
        binding.sum.setOnClickListener {
            binding.sum.setOnClickListener {
                val takeCalc = binding.calc.text.toString()
                val result = evaluate(takeCalc)
                binding.res.text = result.toString()
            }
        }
    }
}