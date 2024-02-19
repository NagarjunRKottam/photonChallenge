package com.interview.photon.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.interview.photon.model.NewYorkSchool
import com.interview.photon.navigation.ScreenRoute
import com.interview.photon.viewmodel.NewYorkSchoolViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewYorkSchoolScreen(
    navController: NavController,
    viewModel: NewYorkSchoolViewModel
) {
    val nySchool by viewModel.nySchool.observeAsState(emptyList())
    LaunchedEffect(Unit) {
        viewModel.fetchNySchool()
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "NewYork School List")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                ),
            )
        }, content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (nySchool.isEmpty()) {
                    Text(text = "Loading")
                } else {
                    NewYorkSchoolList(navController, nySchool)
                }
            }
        })
}

@Composable
fun NewYorkSchoolList(
    navController: NavController,
    schools: List<NewYorkSchool>
) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(schools) { school ->
            Text(text = "${school.dbn} ${school.school_name}",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(
                            route =
                            ScreenRoute.Details.passDetails(school.overview_paragraph)
                        )
                    }
                    .padding(16.dp))
            Divider()
        }
    }
}


