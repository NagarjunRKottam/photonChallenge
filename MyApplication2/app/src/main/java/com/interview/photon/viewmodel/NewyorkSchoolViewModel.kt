package com.interview.photon.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.photon.model.NewYorkSchool
import com.interview.photon.model.NewYorkSchoolRepository
import kotlinx.coroutines.launch

class NewyorkSchoolViewModel(private val repository: NewYorkSchoolRepository) : ViewModel(){

    private val _nySchool = MutableLiveData<List<NewYorkSchool>>()
    val nySchool : LiveData<List<NewYorkSchool>> = _nySchool

    fun fetchNySchool(){
        viewModelScope.launch {
            try {
                _nySchool.value = repository.getNewYorkSchool()
            } catch (ex: Exception){
                Log.d("TAG", "fetchNySchool: " + ex.message)
            }
        }
    }

}