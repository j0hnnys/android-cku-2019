package com.example.week4

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

object TwitterApi {

    fun sendTweet(tweet: Tweet) {
        val tweetRef = FirebaseDatabase.getInstance().getReference("tweets")
        val childTweetRef = tweetRef.child(tweet.timestamp.toString())
        childTweetRef.setValue(tweet)
        tweetRef.push()
    }

    fun listenForTweets(listener: ValueEventListener) {
        val tweetRef = FirebaseDatabase.getInstance().getReference("tweets")
        tweetRef.addValueEventListener(listener)
    }
}
