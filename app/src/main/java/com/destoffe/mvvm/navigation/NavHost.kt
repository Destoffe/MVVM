package com.destoffe.mvvm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.destoffe.mvvm.AnimalViewModel
import com.destoffe.mvvm.detail.DetailScreen
import com.destoffe.mvvm.edit.MainScreen

@Composable
fun NavHostManager(viewModel: AnimalViewModel){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home"
    ){
        composable(route = "home") {
            MainScreen(viewModel = viewModel, navController = navController )
        }

        composable(
            route = "detail/{content}",
            arguments = listOf(
                navArgument("content"){
                    type = NavType.StringType
                }
            )
        ){
            DetailScreen(navController = navController, text = it.arguments?.getString("content"))
        }
    }
}