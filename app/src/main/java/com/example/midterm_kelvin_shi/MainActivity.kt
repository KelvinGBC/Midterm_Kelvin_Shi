package com.example.midterm_kelvin_shi
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
class MainActivity : AppCompatActivity() {
    private lateinit var numberInput: EditText
    private lateinit var generateButton: Button
    private lateinit var historyButton: Button
    private lateinit var listView: ListView
    private val tableList = ArrayList<String>()
    private lateinit var adapter: ArrayAdapter<String>
    companion object {
        val historyList = ArrayList<Int>()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberInput = findViewById(R.id.numberInput)
        generateButton = findViewById(R.id.generateButton)
        historyButton = findViewById(R.id.historyButton)
        listView = findViewById(R.id.listView)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, tableList)
        listView.adapter = adapter

        generateButton.setOnClickListener { generateTable() }
        listView.setOnItemClickListener { _, _, position, _ -> confirmDelete(position) }
        historyButton.setOnClickListener { openHistory() }
    }
    private fun generateTable() {
        val input = numberInput.text.toString()
        if (input.isEmpty()) {
            Toast.makeText(this, "Enter a number", Toast.LENGTH_SHORT).show()
            return
        }

        val num = input.toInt()
        tableList.clear()

        for (i in 1..10) {
            tableList.add("$num × $i = ${num * i}")
        }

        adapter.notifyDataSetChanged()

        if (!historyList.contains(num)) {
            historyList.add(num)
        }
    }
    private fun confirmDelete(position: Int) {
        val item = tableList[position]
        AlertDialog.Builder(this)
            .setTitle("Delete Row")
            .setMessage("Delete $item?")
            .setPositiveButton("Yes") { _, _ ->
                tableList.removeAt(position)
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "Deleted: $item", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No", null)
            .show()
    }
    private fun openHistory() {
        val intent = Intent(this, HistoryActivity::class.java)
        startActivity(intent)
    }
}
