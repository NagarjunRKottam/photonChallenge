package com.interview.photon.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.interview.photon.screen.NewYorkSchoolDetails
import com.interview.photon.screen.NewYorkSchoolScreen
import com.interview.photon.viewmodel.NewYorkSchoolViewModel

const val DETAILS_ARGUMENT_KEY = "details"

//sealed class for screen routes
sealed class ScreenRoute(val route: String) {
    object Home : ScreenRoute("home_screen")
    object Details : ScreenRoute("details_screen/{$DETAILS_ARGUMENT_KEY}") {
        fun passDetails(details: String): String {
            return "details_screen/$details"
        }
    }
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.Home.route
    ) {
        //The Home screen composable
        composable(route = ScreenRoute.Home.route) {
            val viewModel = viewModel<NewYorkSchoolViewModel>()
            NewYorkSchoolScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(
            route = ScreenRoute.Details.route,
            arguments = listOf(navArgument(DETAILS_ARGUMENT_KEY) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val schoolDetails = backStackEntry.arguments?.getString(DETAILS_ARGUMENT_KEY)
            if (schoolDetails != null) {
                // Navigate to the Details screen
                NewYorkSchoolDetails(navController, schoolDetails)
            } else {
                // Handle the case where schoolDetails is null
                Log.e("NavGraph", "School details is null")
            }
        }
    }
}
