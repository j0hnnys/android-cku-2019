package com.example.week4

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TextAdapter : RecyclerView.Adapter<TextViewHolder>() {

    private val tweets: MutableList<Tweet> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        return TextViewHolder(parent)
    }

    override fun getItemCount() = tweets.size

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        holder.bind(tweets[position])
    }

    fun updateTweets(newTweets: List<Tweet>) {
        tweets.clear()
        tweets.addAll(newTweets)
        notifyDataSetChanged()
    }
}
