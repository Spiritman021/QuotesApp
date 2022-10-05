package com.tworoot2.quotesapp.ViewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.tworoot2.quotesapp.DataClasses.Quotes

class MainViewModel(private val context: Context) : ViewModel() {
    private var quotesList: Array<Quotes> = emptyArray()
     var index = 0

    init {
        quotesList = loadQuotesFromAssets()
    }

    private fun loadQuotesFromAssets(): Array<Quotes> {
        val inputStream = context.assets.open("quotes.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer,Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json,Array<Quotes>::class.java)
    }

    fun getQuotes() = quotesList[index]

    fun nextQuotes() =  quotesList[++index]

    fun previousQuotes() = quotesList[--index]

}