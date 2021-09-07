package com.example.searchmovies.model.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MainBroadcastReceiver(text: String) : BroadcastReceiver() {
    private val str: String = text
    override fun onReceive(context: Context, intent: Intent) {
        StringBuilder().apply {
            append("$str\n")
            toString().also {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }
    }
}