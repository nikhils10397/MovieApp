package com.example.movieapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.models.Movie
import com.example.movieapp.shortlistClickListener

class HomepageAdapter(val param: shortlistClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val popularAdapter: MoviesAdapter? = null
    val toprated: MoviesAdapter? = null
    var MovieList: HashMap<Int, MutableList<Movie.Movie>> = hashMapOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_widget_view, parent, false)
        return MovieWidgetViewHolder(view)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MovieWidgetViewHolder) {
            if (position == 0)
                holder.bindPopularMovies(MovieList[0], param, position)
            else
                holder.bindPopularMovies(MovieList[1], param, position)
        }
    }

    override fun getItemCount(): Int {
        return MovieList.size
    }

    fun submitList(list: MutableList<Movie.Movie>, type: Int) {

        MovieList[type] = list
        notifyDataSetChanged()
    }

    class MovieWidgetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tv = itemView.findViewById<TextView>(R.id.rvHeading)
        private val rv = itemView.findViewById<RecyclerView>(R.id.moviesRv)
        var popularAdapter: MoviesAdapter? = null

        fun bindPopularMovies(
            mutableList: MutableList<Movie.Movie>?,
            param: shortlistClickListener,
            position: Int
        ) {
            if (position == 0) {
                tv.text = "Popular Movies"
            }else{
                tv.text = "Latest Movies"
            }
            rv.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            if (popularAdapter == null) {
                popularAdapter = MoviesAdapter(param)
                rv.adapter = popularAdapter
            }
            popularAdapter!!.submitList(mutableList ?: mutableListOf())
//            rv.adapter = MoviesAdapter(param).apply {
//                mutableList?.let {
//                    submitList(it)
//                }
////                submitList(mutableList)
//            }
        }
    }

}