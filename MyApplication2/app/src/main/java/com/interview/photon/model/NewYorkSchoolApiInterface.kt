package com.interview.photon.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface NewYorkSchoolApiInterface {
    @GET("resource/s3k6-pzi2.json")
    suspend fun getNewYorkSchools(): List<NewYorkSchool>
}

object RetrofitInstance{
   private val BASE_URL = "https://data.cityofnewyork.us/"

   private val retrofit: Retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val newyorkSchoolService : NewYorkSchoolApiInterface by lazy {
        retrofit.create(NewYorkSchoolApiInterface::class.java)
    }
}

class NewYorkSchoolRepository{
    private val newYorkSchoolService = RetrofitInstance.newyorkSchoolService

    suspend fun getNewYorkSchool(): List<NewYorkSchool>{
        return newYorkSchoolService.getNewYorkSchools()
    }
}