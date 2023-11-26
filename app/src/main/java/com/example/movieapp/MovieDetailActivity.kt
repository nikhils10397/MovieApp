package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.movieapp.viewmodels.MovieDetailViewModel
import org.w3c.dom.Text

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var mViewModel: MovieDetailViewModel
            override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movie_detail)
                val iv = findViewById<ImageView>(R.id.movieImage)
                val tv = findViewById<TextView>(R.id.movieTitle)
                val desc = findViewById<TextView>(R.id.overviewDesc)
                mViewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java).apply {
                    movieId = intent.extras?.getString("MOVIE_ID").toString()
                }

        mViewModel.getMovieDetails()
        mViewModel.movieDetail.observe(this,{
            val url= if(it.posterPath.isNullOrEmpty())it.backdropPath else it.posterPath
            Glide.with(iv).load("https://image.tmdb.org/t/p/w342" + url).into(iv)
            tv.text = it.title
            desc.text = it.overview
        })
    }
}