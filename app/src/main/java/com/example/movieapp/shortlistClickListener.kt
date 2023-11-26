package com.example.movieapp

import com.example.movieapp.models.Movie

interface shortlistClickListener {

    fun onShortlistClick(movie: Movie.Movie)
}