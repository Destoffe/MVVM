package com.destoffe.mvvm.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
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