package com.example.week4

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cku.twitter.R

class TextViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.text_view_holder, parent, false)
) {
    private val tweet = itemView.findViewById<TextView>(R.id.tweet)
    private val user = itemView.findViewById<TextView>(R.id.user)
    private val timestamp = itemView.findViewById<TextView>(R.id.timestamp)

    fun bind(newTweet: Tweet) {
        tweet.text = newTweet.message
        user.text = newTweet.user
        timestamp.text = newTweet.timestamp.toString()
    }
}

class Tweet @JvmOverloads constructor(
    val message: String = "",
    val user: String = "",
    val timestamp: Long = 0
)
