package com.example.movieapp.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.example.movieapp.MovieDetailActivity
import com.example.movieapp.R
import com.example.movieapp.models.Movie
import androidx.core.content.ContextCompat.startActivity

import android.os.Bundle
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.example.movieapp.localStorageRoom.MovieDatabase
import com.example.movieapp.localStorageRoom.MovieEntity
import com.example.movieapp.shortlistClickListener


class MoviesAdapter(val shortlistClickListener: shortlistClickListener,val isShortlistSection: Boolean = false): RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
      var MovieList : MutableList<Movie.Movie> = mutableListOf()
//    val shortlistListener : shortlistClickListener
//
//    init {
//         shortlistListener = shortlistClickListener
//    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesAdapter.MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_card_item,parent,false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesAdapter.MoviesViewHolder, position: Int) {
        holder.bindData(MovieList[position])
    }

    override fun getItemCount(): Int {
        return MovieList.count()
    }

    fun submitList(list: MutableList<Movie.Movie>){
         MovieList = list
        notifyDataSetChanged()
    }

   inner class MoviesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val iv = itemView.findViewById<ImageView>(R.id.image)
        private val tv = itemView.findViewById<TextView>(R.id.title)
        private val shortlist = itemView.findViewById<LinearLayout>(R.id.shortlist)
        fun bindData(movie: Movie.Movie) {
            if(isShortlistSection){
                shortlist.visibility = View.GONE
            }
            shortlist.setOnClickListener {
               shortlistClickListener.onShortlistClick(movie)
            }
            itemView.setOnClickListener {
                var intent = Intent(it.context,MovieDetailActivity::class.java)
                val bundle = Bundle()

                bundle.putString("MOVIE_ID", movie.id.toString())

                intent.putExtras(bundle)
                startActivity(it.context,intent,null)
                var extra = intent.extras

//                intent.putExtra("movieId",movie.id?.toString())
//                if (extra != null) {
//                    extra?.putString("movieId",movie.id?.toString())
//                    intent.putExtras(extra)
//                }else{
//                    extra = bundleOf()
//                    extra?.putString("movieId",movie.id?.toString())
//                    intent.putExtras(extra)
//                }
//                it.context.startActivity(intent)
            }
            Glide.with(itemView).load("https://image.tmdb.org/t/p/w342${movie.posterPath}").transform(CenterInside()).into(iv)
            tv.text = movie.title
        }
    }

}