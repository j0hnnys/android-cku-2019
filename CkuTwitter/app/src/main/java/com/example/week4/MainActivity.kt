package com.example.week4

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cku.twitter.R
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private val httpClient = OkHttpClient.Builder().build()
    private val textAdapter = TextAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val button = findViewById<Button>(R.id.button)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = textAdapter

        button.setOnClickListener {
            fetchTweet()
            recyclerView.scrollToPosition(0)
        }
    }

    private fun fetchTweet() {
        val request = Request.Builder().url("https://api.myjson.com/bins/85epk").build()

        val call = httpClient.newCall(request)

        call.enqueue(object : okhttp3.Callback {

            override fun onResponse(call: Call, response: Response) {
                val jsonString = response.body()?.string()
                val tweet = JSONObject(jsonString).optString("tweet")
                runOnUiThread {
                    textAdapter.addItem(tweet)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "Failed to fetch tweet", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
