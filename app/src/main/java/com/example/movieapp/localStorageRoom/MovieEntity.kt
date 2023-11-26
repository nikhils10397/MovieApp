package com.example.movieapp.localStorageRoom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movieapp.models.Movie

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true) var uid: Int = 0,
    @ColumnInfo(name = "movieId") var movieId: Long = 0,
    @ColumnInfo(name = "movieTitle") var movieTitle: String = "",
    @ColumnInfo(name = "movieAtw") var movieAtw: String = "",
    @ColumnInfo(name = "time_added") var time: Long = 0L
)