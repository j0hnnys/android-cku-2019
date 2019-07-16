package com.example.week3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val button = findViewById<Button>(R.id.button)
        val textAdapter = TextAdapter()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = textAdapter

        button.setOnClickListener {
            textAdapter.addItem("Hey I'm a new item!")
            recyclerView.scrollToPosition(0)
        }
    }
}
