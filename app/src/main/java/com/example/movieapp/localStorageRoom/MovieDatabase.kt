package com.example.movieapp.localStorageRoom

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.core.content.PackageManagerCompat.LOG_TAG

import android.util.Log
import androidx.core.content.PackageManagerCompat

import androidx.room.Room




@Database(entities = [MovieEntity::class],version = 1)
     abstract class MovieDatabase : RoomDatabase() {

     private val LOCK = Any()

companion object {
     private var sInstance: MovieDatabase? = null

     fun getInstance(context: Context): MovieDatabase? {
          if (sInstance == null) {
               synchronized(this) {
                    Log.d("nikhil", "Creating new database instance")
                    sInstance = Room.databaseBuilder(
                         context.getApplicationContext(),
                         MovieDatabase::class.java, "movie_db"
                    ).allowMainThreadQueries()
                         .build()
               }
          }
          Log.d("nikhil", "Getting the database instance")
          return sInstance
     }
}


     abstract fun getMovieDao(): MovieDao
}