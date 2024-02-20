package com.muratozcan.weatherappwithjetpackcompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.muratozcan.weatherappwithjetpackcompose.screens.HomeScreen
import com.muratozcan.weatherappwithjetpackcompose.screens.WeatherScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home"){
            HomeScreen(navController = navController)
        }
        composable("weather/{location_key}/{name}/{country}", arguments = listOf(
            navArgument("location_key"){
                type = NavType.StringType
            },
            navArgument("name"){
                type = NavType.StringType
            },
            navArgument("country"){
                type = NavType.StringType
            }
        )){
            WeatherScreen(
                navController = navController,
                locationKey = it.arguments?.getString("location_key") ?: "",
                locationName = it.arguments?.getString("name") ?: "",
                country = it.arguments?.getString("country") ?: ""
            )
        }
    }
}