package com.example.movieapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.adapters.HomepageAdapter
import com.example.movieapp.adapters.MoviesAdapter
import com.example.movieapp.models.Movie
import com.example.movieapp.viewmodels.HomepageViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {
    private lateinit var mViewModel: HomepageViewModel
    private var mAdapter: HomepageAdapter? = null
    private var mShortlistAdapter: MoviesAdapter? = null
    private var moviesRecyclerView :RecyclerView? = null
    private var bottomNavbar : BottomNavigationView? = null
    private var shortlistMoviesRecyclerView :RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

    }

    override fun onStart() {
        Log.d("Nikhil","onStart")
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        initData();
    }

    fun initData(){
        moviesRecyclerView = findViewById<RecyclerView>(R.id.moviesRv)
        shortlistMoviesRecyclerView = findViewById<RecyclerView>(R.id.shortlistmoviesRv)
        mViewModel = ViewModelProvider(this).get(HomepageViewModel::class.java).apply {
            context = this@MainActivity
        }
        moviesRecyclerView?.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        shortlistMoviesRecyclerView?.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        mViewModel.movies.observe(this,{
            if(mAdapter == null){
                mAdapter = HomepageAdapter(object: shortlistClickListener{
                    override fun onShortlistClick(movie: Movie.Movie) {

                        mViewModel.storeShortlistedMovie(this@MainActivity,movie)
                    }

                })
                moviesRecyclerView?.adapter = mAdapter

            }

            mAdapter!!.submitList(it.first.movies as MutableList<Movie.Movie>,it.second)
        })
        mViewModel.shortlistedMovies.observe(this,{
            if(mShortlistAdapter == null){
                mShortlistAdapter = MoviesAdapter(object : shortlistClickListener{
                    override fun onShortlistClick(movie: Movie.Movie) {

                    }

                },true)
                shortlistMoviesRecyclerView?.adapter = mShortlistAdapter
            }

            val list: MutableList<Movie.Movie>? = mShortlistAdapter?.MovieList
            list?.add(it)
            mShortlistAdapter?.submitList(list!!)
        })
        mViewModel.getMovies()
        val shortlistedMovies = mViewModel.getShortlistedMovies(this)
        if(mShortlistAdapter == null){
            mShortlistAdapter = MoviesAdapter(object : shortlistClickListener{
                override fun onShortlistClick(movie: Movie.Movie) {

                }

            },true)
            shortlistMoviesRecyclerView?.adapter = mShortlistAdapter
        }
        mShortlistAdapter?.submitList(shortlistedMovies)

//        findViewById<TextView>(R.id.getShortlisted).setOnClickListener {
//            mViewModel.getShortlistedMovies(this)
//        }
        bottomNavbar = findViewById(R.id.bottomnav)
        bottomNavbar?.setOnItemSelectedListener(object: NavigationBarView.OnItemSelectedListener {

            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                if(item.itemId == R.id.search){
                    val intent = Intent(this@MainActivity,SearchActivity::class.java)
                    startActivity(intent)
                }
                return true
            }

        })

    }
}