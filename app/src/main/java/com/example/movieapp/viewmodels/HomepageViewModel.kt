package com.example.movieapp.viewmodels

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.Repository.MoviesRepository
import com.example.movieapp.Repository.MoviesResponseListener
import com.example.movieapp.localStorageRoom.MovieDatabase
import com.example.movieapp.localStorageRoom.MovieEntity
import com.example.movieapp.models.Movie
import kotlin.coroutines.coroutineContext

class HomepageViewModel: ViewModel(),MoviesResponseListener {
    @SuppressLint("StaticFieldLeak")
    var context: Context? = null
    val mRepo = MoviesRepository
    val movies = MutableLiveData<Pair<Movie.GetMoviesResponse,Int>>()
    val shortlistedMovies = MutableLiveData<Movie.Movie>()
     fun getMovies(){
        mRepo.getPopularMovies(1,this)
        mRepo.getLatestMovies(1,this)
    }

    fun getShortlistedMovies(context: Context): MutableList<Movie.Movie> {
        val movieDb:MovieDatabase? = MovieDatabase.getInstance(context)
        val list = movieDb?.getMovieDao()?.getAll()


        val returnList : MutableList<Movie.Movie> = mutableListOf()
        list?.forEach {
            returnList.add(Movie.Movie(it.movieId,it.movieTitle,"",it.movieAtw,"",0f,""))
        }
        return returnList
//        Log.d("nikhil",list.toString())
    }

    fun storeShortlistedMovie(context: Context,movie: Movie.Movie){
        shortlistedMovies.value = movie
        val movieDb: MovieDatabase? = MovieDatabase.getInstance(context)
        movieDb?.getMovieDao()?.insert(MovieEntity(movieTitle = movie.title, movieAtw = movie.posterPath, movieId = movie.id, time = System.currentTimeMillis()))
    }

    override fun OnSuccessResponse(response: Movie.GetMoviesResponse,type: Int) {
        val p:Pair<Movie.GetMoviesResponse,Int> = Pair(response,type)
        movies.value = p
        Toast.makeText(context,"Success Response",Toast.LENGTH_LONG).show()
    }

    override fun OnError(error: String?) {
        TODO("Not yet implemented")
    }
}