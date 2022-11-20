package com.destoffe.mvvm.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.destoffe.mvvm.db.entities.Animal

@Dao
interface AnimalDao {
    @Query("SELECT * FROM animal")
    fun getAll(): LiveData<List<Animal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg animals: Animal)

    @Delete
    fun delete(animal: Animal)
}