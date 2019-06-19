package com.example.week2

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var actionButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.message)
        textView.text = getString(R.string.main_activity_welcome_message)

        actionButton = findViewById(R.id.action_button)
        actionButton.text = getString(R.string.main_activity_action_button_text)
        actionButton.setOnClickListener {
            startActivity(ActivityTwo.makeIntent(this, message = getString(R.string.activity_two_welcome_message)))
        }
    }
}
