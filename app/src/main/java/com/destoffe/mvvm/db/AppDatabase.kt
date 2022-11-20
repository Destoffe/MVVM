package com.destoffe.mvvm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.destoffe.mvvm.db.entities.Animal

@Database(entities = [Animal::class], version = 4)
abstract class AppDatabase: RoomDatabase() {
        abstract fun animalDao(): AnimalDao
}

private lateinit var INSTANCE: AppDatabase

fun getDatabase(context: Context): AppDatabase {
        synchronized(AppDatabase::class.java){
                if(!::INSTANCE.isInitialized){
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java,
                        "database-name")
                                .fallbackToDestructiveMigration()
                                .build()
                }
        }
        return INSTANCE
}