package com.destoffe.mvvm.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.asFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class AnimalRepository(private val database: AppDatabase) {

    suspend fun addAnimal(animal:Animal){
        withContext(Dispatchers.IO){
            database.animalDao().insertAll(animal)
        }
    }

    val animals: Flow<List<Animal>> = database.animalDao().getAll().asFlow()
}