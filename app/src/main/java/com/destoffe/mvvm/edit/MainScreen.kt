package com.destoffe.mvvm.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.destoffe.mvvm.AnimalViewModel
import com.destoffe.mvvm.db.entities.Animal


@Composable
fun MainScreen(navController: NavController,viewModel: AnimalViewModel){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "app bar title") },
                navigationIcon = if (navController.previousBackStackEntry != null) {
                    {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                } else {
                    null
                }

            )
        },
        content = { paddingValues ->
            Inputs(
                viewModel = viewModel,
                navController = navController,
                modifier = Modifier.padding(paddingValues)
            )
        }
    )
}

@Composable
fun Inputs(
    viewModel: AnimalViewModel,
    navController: NavController,
    modifier: Modifier
) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf(0) }
    val animals: List<Animal> by viewModel.animals.collectAsState(listOf())
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(text = "Create an animal")
        TextField(
            value = name,
            onValueChange = { name = it },
            placeholder = { Text(text = "Name of animal") }
        )
        TextField(
            value = age.toString(),
            onValueChange = { age = if (it.isBlank()) 0 else it.toInt() },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
            ),
            placeholder = { Text(text = "Age of animal") }
        )
        Button(
            onClick = { viewModel.addAnimal(Animal(name, age)) }
        ) {
            Text(text = "Add animal")
        }
        AnimalList(list = animals, navController = navController)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AnimalList(list: List<Animal>, navController: NavController) {
    LazyColumn {
        items(
            items = list,
            key = { animals -> animals.id },
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                onClick = { navController.navigate("detail/${it.name}") },
            ) {
                Text(
                    modifier = Modifier.padding(12.dp),
                    text = it.name,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}