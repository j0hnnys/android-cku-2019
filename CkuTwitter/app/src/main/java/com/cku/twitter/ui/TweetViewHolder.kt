package com.cku.twitter.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.cku.twitter.R
import com.cku.twitter.data.model.TweetViewModel

/**
 * This class acts as the view container for a single list item in our list of tweets.
 */
class TweetViewHolder(container: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(container.context).inflate(R.layout.tweet_view, container, false)
) {

    private val tweetContent = ViewCompat.requireViewById<TextView>(itemView, R.id.tweet_content)
    private val tweetInfo = ViewCompat.requireViewById<TextView>(itemView, R.id.tweet_info)

    /**
     * Update our [tweetContent] and [tweetInfo] TextViews with tweetViewModel data.
     * @param tweetViewModel: The tweetViewModel containing the data to display a tweetViewModel in our list.
     */
    fun bind(tweetViewModel: TweetViewModel) {
        tweetContent.text = tweetViewModel.message
        tweetInfo.text = itemView.context.getString(
            R.string.tweet_info,
            tweetViewModel.user,
            tweetViewModel.timestamp.convertToTimestamp()
        )
    }
}
