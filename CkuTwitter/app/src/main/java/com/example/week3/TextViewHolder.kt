package com.example.week3

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TextViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.text_view_holder, parent, false)
) {
    private val textView = itemView.findViewById<TextView>(R.id.text_view)

    fun bind(model: String) {
        textView.text = model
    }
}