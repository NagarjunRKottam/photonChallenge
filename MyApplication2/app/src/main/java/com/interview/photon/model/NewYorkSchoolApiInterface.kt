package com.interview.photon.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


// Interface defining API endpoints for New York schools
interface NewYorkSchoolApiInterface {
    @GET("resource/s3k6-pzi2.json")
    suspend fun getNewYorkSchools(): List<NewYorkSchool>
}

// Singleton object responsible for creating Retrofit instances
object RetrofitInstance{
    private val BASE_URL = "https://data.cityofnewyork.us/"

    private val retrofit: Retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Lazily initialized Retrofit service for New York schools
    val newyorkSchoolService : NewYorkSchoolApiInterface by lazy {
        retrofit.create(NewYorkSchoolApiInterface::class.java)
    }
}

// Repository class for handling New York school data
class NewYorkSchoolRepository{
    // Accessing the Retrofit service for New York schools
    private val newYorkSchoolService = RetrofitInstance.newyorkSchoolService

    // Function to fetch New York school data asynchronously
    suspend fun getNewYorkSchool(): List<NewYorkSchool>{
        return newYorkSchoolService.getNewYorkSchools()
    }
}