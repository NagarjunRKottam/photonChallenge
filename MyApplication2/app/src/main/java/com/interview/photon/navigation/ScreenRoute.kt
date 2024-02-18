package com.interview.photon.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.interview.photon.screen.NewYorkSchoolDetails
import com.interview.photon.screen.NewYorkSchoolScreen
import com.interview.photon.viewmodel.NewyorkSchoolViewModel

const val DETAILS_ARGUMENT_KEY = "details"

sealed class ScreenRoute(val route: String) {
    data object Home : ScreenRoute("home_screen")
    data object Details : ScreenRoute("details_screen/{$DETAILS_ARGUMENT_KEY}") {
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
        composable(route = ScreenRoute.Home.route) {
            val viewModel = viewModel<NewyorkSchoolViewModel>()
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
            val schoolDetails = requireNotNull(
                backStackEntry.arguments?.getString(DETAILS_ARGUMENT_KEY)
            )
            NewYorkSchoolDetails(navController, schoolDetails)
        }
    }
}

