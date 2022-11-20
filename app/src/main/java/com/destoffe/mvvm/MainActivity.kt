package com.destoffe.mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.destoffe.mvvm.db.entities.Animal
import com.destoffe.mvvm.ui.theme.MVVMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: AnimalViewModel = ViewModelProvider(this).get(AnimalViewModel::class.java)

        setContent {
            MVVMTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Inputs (viewModel)
                }
            }
        }
    }
}

@Composable
fun Inputs(
    viewModel: AnimalViewModel,
) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf(0) }
    val animals: List<Animal> by viewModel.animals.collectAsState(listOf())
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Create an animal")
        TextField(
            value = name,
            onValueChange = { name = it},
            placeholder = { Text(text = "Name of animal")}
        )
        TextField(
            value = age.toString(),
            onValueChange = { age = if (it.isBlank()) 0 else it.toInt() },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
            ),
            placeholder = { Text(text = "Age of animal")}
        )
        Button(
            onClick = { viewModel.addAnimal(Animal(name,age)) }
        ) {
            Text(text = "Add animal")
        }
        AnimalList(list = animals)
    }
}

@Composable
fun AnimalList(list: List<Animal>){
    LazyColumn{
        items(
            items = list,
            key = { animals -> animals.id },
        ) {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MVVMTheme {
    }
}