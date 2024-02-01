package com.interview.photon.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.interview.photon.viewmodel.NewyorkSchoolViewModel

@Composable
fun NewYorkSchoolDetails(details: String){
    Column {
            Text(text = details)

    }
}