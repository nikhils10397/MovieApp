package com.example.movieapp.Repository

import android.util.Log
import com.example.movieapp.models.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MoviesRepository {
    private val api: Api

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(Api::class.java)
    }

    fun getPopularMovies(page: Int = 1,listener: MoviesResponseListener ) {
        api.getPopularMovies(page = page)
            .enqueue(object : Callback<Movie.GetMoviesResponse> {
                override fun onResponse(
                    call: Call<Movie.GetMoviesResponse>,
                    response: Response<Movie.GetMoviesResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            listener.OnSuccessResponse(responseBody,0)
                            Log.d("Repository", "Movies: ${responseBody.movies}")
                        } else {
                            Log.d("Repository", "Failed to get response")
                        }
                    }
                }

                override fun onFailure(call: Call<Movie.GetMoviesResponse>, t: Throwable) {
                    Log.e("Repository", "onFailure", t)
                }
            })
    }

    fun getLatestMovies(page: Int = 1,listener: MoviesResponseListener){
        api.getLatestMovies(page = page)
            .enqueue(object : Callback<Movie.GetMoviesResponse>{
            override fun onResponse(
                call: Call<Movie.GetMoviesResponse>,
                response: Response<Movie.GetMoviesResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()

                    if (responseBody != null) {
                        listener.OnSuccessResponse(responseBody,1)
                        Log.d("Repository", "Movies: ${responseBody.movies}")
                    } else {
                        Log.d("Repository", "Failed to get response")
                    }
                }
            }

            override fun onFailure(call: Call<Movie.GetMoviesResponse>, t: Throwable) {
                Log.e("Repository", "onFailure", t)
            }

        })
    }

    fun getMovieByName(name: String,listener: MoviesResponseListener){
        api.getMovieByName(query = name)
            .enqueue(object : Callback<Movie.GetMoviesResponse>{
                override fun onResponse(
                    call: Call<Movie.GetMoviesResponse>,
                    response: Response<Movie.GetMoviesResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            listener.OnSuccessResponse(responseBody,1)
                            Log.d("Repository", "Movies: ${responseBody.movies}")
                        } else {
                            Log.d("Repository", "Failed to get response")
                        }
                    }
                }

                override fun onFailure(call: Call<Movie.GetMoviesResponse>, t: Throwable) {
                    Log.e("Repository", "onFailure", t)
                }

            })
    }

    fun getMovieDetails(movieId: String,listener: MoviesDetailResponseListener ) {
        api.getMovieDetails(movieId)
            .enqueue(object : Callback<Movie.Movie> {
                override fun onResponse(
                    call: Call<Movie.Movie>,
                    response: Response<Movie.Movie>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            listener.OnSuccessResponse(responseBody)
                            Log.d("Repository", "Movies: ${responseBody}")
                        } else {
                            Log.d("Repository", "Failed to get response")
                        }
                    }
                }

                override fun onFailure(call: Call<Movie.Movie>, t: Throwable) {
                    Log.e("Repository", "onFailure", t)
                }
            })
    }
}