package com.example.searchmovies

import android.view.View
import com.example.searchmovies.viewmodel.Constants
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

fun Date.formatStr(): String =
    SimpleDateFormat(Constants.DATE_FORMAT, Locale.getDefault()).format(this)

fun View.showSnackbar(text: String, length: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, text, length).show()
}