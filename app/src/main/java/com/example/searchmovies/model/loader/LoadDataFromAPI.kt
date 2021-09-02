package com.example.searchmovies.model.loader

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.searchmovies.model.gson_model.movie.MovieDTO
import com.example.searchmovies.model.gson_model.trending.TrendingDTO
import com.example.searchmovies.viewmodel.Constants
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection


object LoadDataFromAPI {

    fun loadTrending(): TrendingDTO? {
        val jsonStr: String =
            loadJSON("https://api.themoviedb.org/3/trending/movie/week?api_key=${Constants.API_KEY}&language=ru")
        return Gson().fromJson(jsonStr, TrendingDTO::class.java)
    }

    fun loadMovie(id: Int): MovieDTO? {
        val jsonStr: String =
            loadJSON("https://api.themoviedb.org/3/movie/$id?api_key=${Constants.API_KEY}&language=ru&include_image_language=ru")
        return Gson().fromJson(jsonStr, MovieDTO::class.java)
    }

    private fun loadJSON(url: String): String {
        try {
            val uri = URL(url)

            lateinit var urlConnection: HttpsURLConnection
            try {
                urlConnection = uri.openConnection() as HttpsURLConnection
                urlConnection.requestMethod = "GET"
                urlConnection.readTimeout = 10000
                val bufferedReader = BufferedReader(InputStreamReader(urlConnection.inputStream))

                return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                    getLinesForOld(bufferedReader)
                } else {
                    getLines(bufferedReader)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                urlConnection.disconnect()
            }
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }

        return "null"
    }

    private fun getLinesForOld(reader: BufferedReader): String {
        val rawData = StringBuilder(1024)
        var tempVariable: String?

        while (reader.readLine().also { tempVariable = it } != null) {
            rawData.append(tempVariable).append("\n")
        }

        reader.close()
        return rawData.toString()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }
}