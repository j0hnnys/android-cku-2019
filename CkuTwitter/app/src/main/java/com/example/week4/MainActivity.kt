package com.example.week4

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cku.twitter.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    private val textAdapter = TextAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val button = findViewById<Button>(R.id.button)
        val editText = findViewById<EditText>(R.id.input)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = textAdapter

        button.setOnClickListener {
            val inputText = editText.text.toString()
            val tweet = Tweet(inputText, "Johnny", System.currentTimeMillis())
            sendTweet(tweet)
            recyclerView.scrollToPosition(0)

            editText.setText("")
            closeKeyboard(button)
        }

        listenForTweets()
    }

    fun closeKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun sendTweet(tweet: Tweet) {
        TwitterApi.sendTweet(tweet)
    }

    fun listenForTweets() {
        TwitterApi.listenForTweets(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(data: DataSnapshot) {
                val newTweets: List<Tweet> = data.children.mapNotNull { snapshot ->
                    snapshot.getValue(Tweet::class.java)
                }.reversed()

                textAdapter.updateTweets(newTweets)
            }
        })
    }
}
