package com.example.week4

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TextAdapter : RecyclerView.Adapter<TextViewHolder>() {

    private val models = mutableListOf(
        "hello",
        "goodbye",
        "see ya later"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        return TextViewHolder(parent)
    }

    override fun getItemCount() = models.size

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        holder.bind(models[position])
    }

    fun addItem(model: String) {
        models.add(0, model)
        notifyItemInserted(0)
    }
}