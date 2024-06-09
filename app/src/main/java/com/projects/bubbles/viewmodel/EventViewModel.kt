package com.projects.bubbles.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projects.bubbles.dto.EventInPersonRequestDTO
import com.projects.bubbles.dto.EventOnlineRequestDTO
import com.projects.bubbles.dto.EventResponseDTO
import com.projects.bubbles.services.Service
import com.projects.bubbles.utils.HttpException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EventViewModel : ViewModel() {
    private val eventService = Service.EventService
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        val errorMessage = throwable.message ?: "Erro desconhecido"
        error.postValue(errorMessage)
        Log.e("EventViewModel", errorMessage, throwable)
    }

    val eventList = MutableLiveData<List<EventResponseDTO>>()
    val isLoading = MutableLiveData(false)
    val error = MutableLiveData<String>()

    init {
        getAllEvents()
    }

    fun getAllEvents() = viewModelScope.launch(coroutineExceptionHandler) {
        isLoading.value = true

        val response = withContext(Dispatchers.IO) {
            eventService.getAllEvents()
        }

        withContext(Dispatchers.Main) {
            if (response.isSuccessful) {
                eventList.value = response.body() ?: emptyList()
                Log.d("EventViewModel", "Get events successful: ${response.body()}")
            } else {
                throw HttpException(response)
            }

            isLoading.value = false
        }
    }

    fun createInPersonEvent(eventRequest: EventInPersonRequestDTO) =
        viewModelScope.launch(coroutineExceptionHandler) {
            isLoading.value = true

            Log.d("EventoPresencial", "Payload: " + eventRequest)

            val response = withContext(Dispatchers.IO) {
                eventService.createInPersonEvent(eventRequest)
            }

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    getAllEvents()
                    Log.d("EventViewModel", "Create in-person event successful: ${response.body()}")
                } else {
                    throw HttpException(response)
                }

                isLoading.value = false
            }
        }

    fun createOnlineEvent(eventRequest: EventOnlineRequestDTO) =
        viewModelScope.launch(coroutineExceptionHandler) {
            isLoading.value = true

            val response = withContext(Dispatchers.IO) {
                eventService.createOnlineEvent(eventRequest)
            }

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    getAllEvents()
                    Log.d("EventViewModel", "Create online event successful: ${response.body()}")
                } else {
                    throw HttpException(response)
                }

                isLoading.value = false
            }
        }
}
