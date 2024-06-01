package com.projects.bubbles.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.projects.bubbles.dto.EventResponseDTO
import com.projects.bubbles.services.Service
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EventViewModel : ViewModel() {
    private val eventService = Service.EventService

    val eventList = MutableLiveData<List<EventResponseDTO>>()
    val isLoading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    init {
        getAllEvents()
    }

    fun getAllEvents() {
        isLoading.value = true
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = eventService.getAllEvents()
                if (response.isSuccessful) {
                    eventList.postValue(response.body() ?: emptyList())
                    Log.d("EventViewModel", "Get events successful: ${response.body()}")
                } else {
                    val errorMessage = "Erro ao buscar eventos: ${response.errorBody()?.string()}"
                    error.postValue(errorMessage)
                    Log.e("EventViewModel", errorMessage)
                }
            } catch (e: Exception) {
                val errorMessage = "Erro ao buscar eventos: ${e.message}"
                error.postValue(errorMessage)
                Log.e("EventViewModel", errorMessage)
            } finally {
                isLoading.postValue(false)
            }
        }
    }

}
