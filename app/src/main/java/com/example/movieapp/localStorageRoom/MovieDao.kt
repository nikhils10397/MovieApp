package com.example.movieapp.localStorageRoom

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.movieapp.models.Movie

@Dao
 interface MovieDao {

    @Query("SELECT * from movies")
    fun getAll() : List<MovieEntity>

    @Insert
    fun insert(vararg movies: MovieEntity)
}