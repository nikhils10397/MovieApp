package com.example.movieapp.Repository

import com.example.movieapp.models.Movie

interface MoviesResponseListener {
    fun OnSuccessResponse(response: Movie.GetMoviesResponse,type:Int)
    fun OnError(error: String?)
}

interface MoviesDetailResponseListener {
    fun OnSuccessResponse(response: Movie.Movie)
    fun OnError(error: String?)
}