package com.example.movieapp.viewmodels

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.MovieDetailActivity
import com.example.movieapp.Repository.MoviesDetailResponseListener
import com.example.movieapp.Repository.MoviesRepository
import com.example.movieapp.Repository.MoviesResponseListener
import com.example.movieapp.SearchActivity
import com.example.movieapp.models.Movie

class MovieDetailViewModel: ViewModel(),MoviesDetailResponseListener,MoviesResponseListener {
    @SuppressLint("StaticFieldLeak")
    var context: Context? = null
    val mRepo = MoviesRepository
    var movieId : String = ""
    val movieDetail = MutableLiveData<Movie.Movie>()
    fun getMovieDetails(){
        mRepo.getMovieDetails(movieId,this)
    }

    fun getMovieByName(name: String){
        mRepo.getMovieByName(name,this)
    }

    override fun OnSuccessResponse(response: Movie.Movie) {
        movieDetail.value = response
    }

    override fun OnSuccessResponse(response: Movie.GetMoviesResponse, type: Int) {
        if (context is SearchActivity) {
            var intent = Intent(context, MovieDetailActivity::class.java)
            val bundle = Bundle()

            bundle.putString("MOVIE_ID", response.movies.firstOrNull()?.id.toString())

            intent.putExtras(bundle)
            ContextCompat.startActivity(context as SearchActivity, intent, null)
        }
        Log.d("nikhil",response.toString())
    }

    override fun OnError(error: String?) {
        TODO("Not yet implemented")
    }
}