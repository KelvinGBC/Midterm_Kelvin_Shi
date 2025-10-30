package com.example.midterm_kelvin_shi
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        val listView: ListView = findViewById(R.id.historyListView)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            MainActivity.historyList
        )
        listView.adapter = adapter
    }
}