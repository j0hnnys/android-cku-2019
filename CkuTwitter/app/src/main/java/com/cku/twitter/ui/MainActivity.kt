package com.cku.twitter.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cku.twitter.R
import com.cku.twitter.data.model.TweetViewModel
import com.cku.twitter.data.network.TweetService
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var editText: EditText
    private lateinit var submitButton: Button

    private val tweetAdapter = TweetAdapter()
    private val name = "Enter name here" // TODO: Add your name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = ActivityCompat.requireViewById(this, R.id.recycler_view)
        editText = ActivityCompat.requireViewById(this, R.id.edit_text)
        submitButton = ActivityCompat.requireViewById(this, R.id.submit_button)

        /**
         * At a minimum, a RecyclerView requires an adapter and a layout manager to display a list in the UI.
         */
        recyclerView.adapter = tweetAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        submitButton.setOnClickListener {
            sendTweet()
            editText.setText("")
            closeSoftKeyboard()
        }
    }

    override fun onResume() {
        super.onResume()
        registerTweetListener()
    }

    private fun sendTweet() {
        TweetService.sendTweet(
            TweetViewModel(
                message = editText.text.toString(),
                user = name,
                timestamp = System.currentTimeMillis()
            )
        )
    }

    private fun closeSoftKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(editText.windowToken, 0)
    }

    private fun registerTweetListener() {
        TweetService.listenForTweets(object : ValueEventListener {
            /**
             * This function will automatically trigger every time our database is updated with new tweets.
             * @param data The snapshot of the database at the time that the update occurred.
             */
            override fun onDataChange(data: DataSnapshot) {

                // Transform the snapshot into a list of tweets
                val tweetViewModels: List<TweetViewModel> = data.children.mapNotNull { snapshot ->
                    snapshot.getValue(TweetViewModel::class.java)
                }.reversed()

                // Update the data in our adapter, which will propagate to the RecyclerView list
                tweetAdapter.updateTweets(tweetViewModels)
                recyclerView.smoothScrollToPosition(0)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(this@MainActivity::class.java.simpleName, "Error updating tweets")
            }
        })
    }
}
