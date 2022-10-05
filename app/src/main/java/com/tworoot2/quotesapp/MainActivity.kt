package com.tworoot2.quotesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import com.tworoot2.quotesapp.DataClasses.Quotes
import com.tworoot2.quotesapp.ViewModel.MainViewModel
import com.tworoot2.quotesapp.ViewModel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(application))[MainViewModel::class.java]
        setQuote(mainViewModel.getQuotes())

    }

    fun nextClicked(v: View) {
        setQuote(mainViewModel.nextQuotes())
    }

    fun previousClicked(v: View) {
        if (mainViewModel.index == 0) {
            Toast.makeText(this@MainActivity, "Not available", Toast.LENGTH_SHORT).show()
        } else {
            setQuote(mainViewModel.previousQuotes())
        }
    }

    fun shareClicked(v: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, mainViewModel.getQuotes().text)
        startActivity(intent)
    }

    fun setQuote(quotes: Quotes) {
        quotesText.text = quotes.text
        publisher.text = quotes.author
    }

    private val publisher: TextView
        get() = findViewById(R.id.publisher)

    private val quotesText: TextView
        get() = findViewById(R.id.quotes)

    private val previous: Button
        get() = findViewById(R.id.previous)

    private val next: Button
        get() = findViewById(R.id.next)

    private val share: CardView
        get() = findViewById(R.id.share)
}