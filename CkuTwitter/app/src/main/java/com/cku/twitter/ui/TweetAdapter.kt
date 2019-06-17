package com.cku.twitter.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cku.twitter.R
import com.cku.twitter.data.model.TweetViewModel

/**
 * The adapter class bridges the data to a ViewHolder in a RecyclerView list. It will handle
 * inflating the ViewHolder layout
 */
class TweetAdapter : RecyclerView.Adapter<TweetViewHolder>() {

    var tweets = emptyList<TweetViewModel>()

    fun updateTweets(newTweetViewModels: List<TweetViewModel>) {
        tweets = newTweetViewModels
        notifyDataSetChanged()
    }

    /**
     * We create a view that has the layout specific in [R.layout.tweet_view].
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        return TweetViewHolder(parent)
    }

    /**
     * Every instance of [ListAdapter] will have a list of tweets. Whenever the user scrolls to the
     * nth item in the list represented by [position], then there will be a ViewHolder.
     */
    override fun onBindViewHolder(viewHolder: TweetViewHolder, position: Int) {
        viewHolder.bind(tweets[position])
    }

    override fun getItemCount() = tweets.size
}

/**
 * A [ListAdapter] uses an instance of [DiffUtil.ItemCallback] to determine what ViewHolders
 *
 * This class extends [DiffUtil.ItemCallback] and uses two comparison methods
 * to determine whether two tweets are the same.
 */
//private class TweetDiffUtilCallback : DiffUtil.ItemCallback<TweetViewModel>() {
//    override fun areItemsTheSame(oldItem: TweetViewModel, newItem: TweetViewModel): Boolean {
//        return oldItem.timestamp == newItem.timestamp
//    }
//
//    override fun areContentsTheSame(oldItem: TweetViewModel, newItem: TweetViewModel): Boolean {
//        return oldItem.message == newItem.message && oldItem.user == newItem.user
//    }
//}
