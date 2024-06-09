package com.projects.bubbles.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projects.bubbles.dto.BubbleRequestDTO
import com.projects.bubbles.dto.BubbleResponseDTO
import com.projects.bubbles.services.Service
import com.projects.bubbles.utils.HttpException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class BubbleViewModel : ViewModel() {
    private val bubbleService = Service.BubbleService
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        val errorMessage = throwable.message ?: "Erro desconhecido"
        erro.postValue(errorMessage)
        Log.e("BubbleViewModel", errorMessage, throwable)
    }

    val bubbleList = MutableLiveData<List<BubbleResponseDTO>>()
    val erro = MutableLiveData<String>()
    val isLoading = MutableLiveData(false)

    init {
        getBubbles()
    }

    fun getBubbles() = viewModelScope.launch(coroutineExceptionHandler) {
        isLoading.value = true

        val response = withContext(Dispatchers.IO) {
            bubbleService.getAllBubbles()
        }

        withContext(Dispatchers.Main) {
            if (response.isSuccessful) {
                bubbleList.value = response.body() ?: emptyList()
                Log.d("BubbleViewModel", "Get bubbles successful: ${response.body()}")
            } else {
                throw HttpException(response)
            }

            delay(2000)
            isLoading.value = false
        }
    }

    fun createBubble(bubbleRequest: BubbleRequestDTO) = viewModelScope.launch(coroutineExceptionHandler) {
        isLoading.value = true

        withContext(Dispatchers.IO) {
            val response = bubbleService.createNewBubble(bubbleRequest)

            if (response.isSuccessful) {
                getBubbles()
                Log.d("BubbleViewModel", "Create bubble successful: ${response.body()}")
            } else {
                throw HttpException(response)
            }
        }
    }

    fun updateBubble(bubbleId: Int, updatedBubble: BubbleResponseDTO) = viewModelScope.launch(coroutineExceptionHandler) {
        isLoading.value = true

        withContext(Dispatchers.IO) {
            val response = bubbleService.updateBubbleById(bubbleId, updatedBubble)

            if (response.isSuccessful) {
                getBubbles()
                Log.d("BubbleViewModel", "Update bubble successful: ${response.body()}")
            } else {
                throw HttpException(response)
            }
        }
    }

    fun deleteBubble(bubbleId: Int) = viewModelScope.launch(coroutineExceptionHandler) {
        isLoading.value = true

        withContext(Dispatchers.IO) {
            val response = bubbleService.deleteBubbleById(bubbleId)
            if (response.isSuccessful) {
                getBubbles()
                Log.d("BubbleViewModel", "Delete bubble successful")
            } else {
                throw HttpException(response)
            }
        }
    }
}
