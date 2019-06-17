package com.cku.twitter.data.network

import com.cku.twitter.data.api.TwitterApi
import com.cku.twitter.data.model.TweetViewModel
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/**
 * This class provides the implementation of our [TwitterApi].
 */
object TweetService : TwitterApi {
    private const val BASE_TWEET_REF = "tweets"

    /**
     * Pushes a tweetViewModel to our database of tweets.
     */
    override fun sendTweet(tweetViewModel: TweetViewModel) {
        val tweetRef = getBaseTweetRef()
        val newTweetRef = tweetRef.child(tweetViewModel.timestamp.toString())
        newTweetRef.setValue(tweetViewModel)
        tweetRef.push()
    }

    /**
     * Registers a listener that notifies you of whenever
     * there are new tweets pushed the database.
     */
    override fun listenForTweets(listener: ValueEventListener) {
        val tweetRef = getBaseTweetRef()
        tweetRef.addValueEventListener(listener)
    }

    /**
     * This function returns a reference to the field in
     * our database that is the root of all our tweets.
     */
    private fun getBaseTweetRef() = FirebaseDatabase.getInstance().getReference(BASE_TWEET_REF)
}
