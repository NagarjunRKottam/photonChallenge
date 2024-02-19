package com.interview.photon.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.photon.model.NewYorkSchool
import com.interview.photon.model.NewYorkSchoolRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class NewYorkSchoolViewModel : ViewModel() {
    // Repository for accessing New York school data
    private var repository: NewYorkSchoolRepository = NewYorkSchoolRepository()

    // MutableLiveData to hold the list of New York schools
    private val _nySchool = MutableLiveData<List<NewYorkSchool>>()
    val nySchool: LiveData<List<NewYorkSchool>> = _nySchool

    // Coroutine exception handler for error handling during coroutine execution
    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.e(TAG, "Coroutine Exception: ${exception.message}", exception)
    }

    // Function to set the repository
    fun setRepository(repo: NewYorkSchoolRepository) {
        repository = repo
    }

    // Function to fetch New York school data asynchronously
    fun fetchNySchool() {
        // Using viewModelScope to launch coroutine tied to ViewModel's lifecycle
        viewModelScope.launch(exceptionHandler) {
            // Fetch data from the repository asynchronously and update the MutableLiveData
            _nySchool.value = repository.getNewYorkSchool()
        }
    }

    companion object {
        private const val TAG = "NewyorkSchoolViewModel"
    }
}