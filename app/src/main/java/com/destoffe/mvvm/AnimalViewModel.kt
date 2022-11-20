package com.destoffe.mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.destoffe.mvvm.db.entities.Animal
import com.destoffe.mvvm.db.getDatabase
import com.destoffe.mvvm.repository.AnimalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnimalViewModel(application: Application): AndroidViewModel(application) {
    private val animalRepository = AnimalRepository(getDatabase(application))

    val animals = animalRepository.animals

     fun addAnimal(animal: Animal){
         viewModelScope.launch {
             withContext(Dispatchers.IO){
                 animalRepository.addAnimal(animal)
             }
         }
    }
}