package com.interview.photon.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.interview.photon.model.NewYorkSchool
import com.interview.photon.viewmodel.NewyorkSchoolViewModel

@Composable
fun NewYorkSchoolScreen(viewModel : NewyorkSchoolViewModel){

    val nySchool by viewModel.nySchool.observeAsState(emptyList())

    LaunchedEffect(Unit){
        viewModel.fetchNySchool()
    }
    Column {
        if(nySchool.isEmpty()){
            Text(text = "Loading")
        } else {
            NewYorkSchoolList(nySchool)
        }
    }
}


@Composable
fun NewYorkSchoolList(schools:List<NewYorkSchool>){
    LazyColumn {
       items(schools){school ->
           Text(text = school.school_name,
               modifier = Modifier.fillMaxWidth()
                   .clickable {}
                   .padding(16.dp))
       }
    }
}
