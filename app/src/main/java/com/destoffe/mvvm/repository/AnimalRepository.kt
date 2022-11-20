package com.destoffe.mvvm.repository

import androidx.lifecycle.asFlow
import com.destoffe.mvvm.db.entities.Animal
import com.destoffe.mvvm.db.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class AnimalRepository(private val database: AppDatabase) {

    suspend fun addAnimal(animal: Animal){
        withContext(Dispatchers.IO){
            database.animalDao().insertAll(animal)
        }
    }

    val animals: Flow<List<Animal>> = database.animalDao().getAll().asFlow()
}