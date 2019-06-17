package com.cku.twitter.data.network

import androidx.annotation.UiThread
import com.cku.twitter.data.model.TweetViewModel
import kotlin.random.Random

/**
 * Class for educational purposes only.
 */
object FlakyTweetService {

    /**
     * TweetViewModel "API" that has 50% chance of failing.
     */
    fun postTweet(
        tweetViewModel: TweetViewModel,
        listener: FlakyTweetListener
    ) {
        if (Random.nextBoolean()) {
            // TODO: Return updated list of tweets
            Thread().run { listener.onSuccess(listOf(tweetViewModel)) }
        } else {
            listener.onFailure("uh oh, something went wrong")
        }
    }
}

interface FlakyTweetListener {
    @UiThread
    fun onSuccess(newTweetViewModels: List<TweetViewModel>)
    @UiThread
    fun onFailure(errorMessage: String)
}
