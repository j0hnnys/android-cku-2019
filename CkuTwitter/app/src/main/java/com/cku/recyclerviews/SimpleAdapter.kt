package com.recyclerviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cku.twitter.R

class SimpleAdapter(var listData: List<String>) : RecyclerView.Adapter<SimpleViewHolder>() {

    override fun onCreateViewHolder(container: ViewGroup, viewType: Int): SimpleViewHolder {
        val view = LayoutInflater.from(container.context).inflate(R.layout.list_item_layout, container, false)!!
        return SimpleViewHolder(view)
    }

    /* Returns the number of items in the RecyclerView. This should be the size of your data set */
    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        val text = listData[position]
        holder.bind(text)
    }
}

class SimpleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val textView = view.findViewById<TextView>(R.id.text)

    fun bind(text: String) {
        textView.text = text
    }
}
