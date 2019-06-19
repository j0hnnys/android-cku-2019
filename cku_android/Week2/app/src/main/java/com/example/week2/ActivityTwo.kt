package com.example.week2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ActivityTwo : AppCompatActivity() {

    private lateinit var messageTextView: TextView

    private var message: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two_main)

        message = intent?.getStringExtra(MESSAGE_KEY)

        messageTextView = findViewById(R.id.message)

        message?.let {
            messageTextView.text = it
        }
    }

    companion object {
        private const val MESSAGE_KEY = "message_key"

        fun makeIntent(context: Context, message: String): Intent {
            return Intent(context, ActivityTwo::class.java).apply {
                putExtra(MESSAGE_KEY, message)
            }
        }
    }
}
