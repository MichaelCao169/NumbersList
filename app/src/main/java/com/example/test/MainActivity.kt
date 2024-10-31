package com.example.test
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private lateinit var editTextNumber: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var buttonShow: Button
    private lateinit var listView: ListView
    private lateinit var textViewError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        editTextNumber = findViewById(R.id.editTextNumber)
        radioGroup = findViewById(R.id.radioGroup)
        buttonShow = findViewById(R.id.buttonShow)
        listView = findViewById(R.id.listView)
        textViewError = findViewById(R.id.textViewError)

        buttonShow.setOnClickListener {
            showNumbers()
        }
    }

    private fun showNumbers() {

        textViewError.text = ""


        val input = editTextNumber.text.toString()
        if (input.isEmpty()) {
            textViewError.text = "Vui lòng nhập số"
            return
        }

        val n = input.toIntOrNull()
        if (n == null || n < 0) {
            textViewError.text = "Vui lòng nhập số nguyên dương hợp lệ"
            return
        }


        val numbers = when (radioGroup.checkedRadioButtonId) {
            R.id.radioButtonEven -> getEvenNumbers(n)
            R.id.radioButtonOdd -> getOddNumbers(n)
            R.id.radioButtonPerfectSquare -> getPerfectSquareNumbers(n)
            else -> {
                textViewError.text = "Vui lòng chọn loại số"
                return
            }
        }


        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
        listView.adapter = adapter
    }

    private fun getEvenNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 == 0 }
    }

    private fun getOddNumbers(n: Int): List<Int> {
        return (1..n).filter { it % 2 != 0 }
    }

    private fun getPerfectSquareNumbers(n: Int): List<Int> {
        val maxSquareRoot = sqrt(n.toDouble()).toInt()
        return (0..maxSquareRoot).map { it * it }
    }
}