package com.example.movieapp.Repository

import com.example.movieapp.models.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = Constants.apiKey,
        @Query("page") page: Int
    ): Call<Movie.GetMoviesResponse>

    @GET("search/movie")
    fun getMovieByName(
        @Query("api_key") apiKey: String = Constants.apiKey,
        @Query("query") query: String
    ): Call<Movie.GetMoviesResponse>

    @GET("movie/{id}")
    fun getMovieDetails(
        @Path("id") movieId: String,
        @Query("api_key") apiKey: String = Constants.apiKey
    ): Call<Movie.Movie>

    @GET("movie/top_rated")
    fun getLatestMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = Constants.apiKey
    ): Call<Movie.GetMoviesResponse>
}