package com.cku.networkingexample

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cku.twitter.R
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class NetworkingActivity : AppCompatActivity() {

    private lateinit var tweetText: TextView
    private lateinit var fetchTweetButton: Button

    private val httpClient = OkHttpClient.Builder().build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_networking)

        tweetText = findViewById(R.id.tweet_text)
        fetchTweetButton = findViewById(R.id.button)

        fetchTweetButton.setOnClickListener {
            fetchTweet()
        }
    }

    private fun fetchTweet() {

        val request = Request.Builder().url("https://api.myjson.com/bins/85epk").build()

        val call = httpClient.newCall(request)

        // FIXME: NetworkOnMainThreadException
        call.execute()

        // Note: OkHTTP will perform this networking operation on a background thread for you.
        call.enqueue(object : okhttp3.Callback {

            override fun onResponse(call: Call, response: Response) {
                val jsonString = response.body()?.string()
                val tweet = JSONObject(jsonString).optString("tweet")

                // FIXME: CalledFromWrongThreadException
                tweetText.text = tweet

                runOnUiThread {
                    tweetText.text = tweet
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                Toast.makeText(this@NetworkingActivity, "Failed to fetch tweet", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
