package com.example.searchmovies.model.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MainBroadcastReceiver(str: String) : BroadcastReceiver() {
    private val text: String = str
    override fun onReceive(context: Context, intent: Intent) {
        StringBuilder().apply {
            append(text)
            toString().also {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }
    }
}