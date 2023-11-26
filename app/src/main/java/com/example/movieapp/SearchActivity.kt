package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.text.toSpannable
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.viewmodels.HomepageViewModel
import com.example.movieapp.viewmodels.MovieDetailViewModel
import com.google.android.material.textfield.TextInputEditText

class SearchActivity : AppCompatActivity() {
    private lateinit var mViewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
    }

    override fun onStart() {
        super.onStart()
        initView()
        mViewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java).apply {
            context = this@SearchActivity
        }
    }

    fun initView(){
        val tv= findViewById<TextInputEditText>(R.id.inputText)
        val btnSearch = findViewById<Button>(R.id.btnSearch)

        tv.addTextChangedListener {
            btnSearch.isEnabled = it?.length != 0
        }

        btnSearch.setOnClickListener {
            mViewModel.getMovieByName(tv.editableText.toSpannable().toString())
        }
    }
}