package com.interview.photon.viewmodel

import androidx.lifecycle.viewmodel.compose.viewModel
import com.interview.photon.model.NewYorkSchool
import com.interview.photon.model.NewYorkSchoolApiInterface
import com.interview.photon.model.NewYorkSchoolRepository
import com.interview.photon.model.RetrofitInstance
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class NewyorkSchoolViewModelTest {

   lateinit var repository: NewYorkSchoolRepository
   lateinit var viewModel: NewyorkSchoolViewModel

   @Before
   fun setUp(){
       val retrofit =
           Retrofit.Builder()
               .baseUrl("https://data.cityofnewyork.us/resource/s3k6-pzi2.json")
               .addConverterFactory(GsonConverterFactory.create())
               .build()
       val apiService = retrofit.create(NewYorkSchoolApiInterface::class.java)
       viewModel = NewyorkSchoolViewModel(repository)
   }

    @Test
    fun  fetchNewYorkSchoolTest(){
        viewModel.fetchNySchool()
        val school = viewModel.nySchool.value
        school?.let { assertTrue(it.isNotEmpty()) }
    }

}