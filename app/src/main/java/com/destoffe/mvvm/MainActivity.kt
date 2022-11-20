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
import androidx.navigation.NavController
import com.destoffe.mvvm.db.entities.Animal
import com.destoffe.mvvm.navigation.NavHostManager
import com.destoffe.mvvm.ui.theme.MVVMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: AnimalViewModel = ViewModelProvider(this)[AnimalViewModel::class.java]

        setContent {
            MVVMTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MVVMApp(viewModel)
                }
            }
        }
    }
}


@Composable
fun MVVMApp(viewModel: AnimalViewModel) {
    NavHostManager(viewModel = viewModel)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MVVMTheme {
    }
}