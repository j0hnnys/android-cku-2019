package com.cku.twitter.data.api

import com.cku.twitter.data.model.TweetViewModel
import com.google.firebase.database.ValueEventListener

/**
 * This interface defines the API our Twitter service.
 */
interface TwitterApi {
    fun sendTweet(tweetViewModel: TweetViewModel)

    fun listenForTweets(listener: ValueEventListener)
}
