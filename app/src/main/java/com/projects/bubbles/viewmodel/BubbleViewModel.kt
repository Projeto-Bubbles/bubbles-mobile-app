package com.projects.bubbles.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.projects.bubbles.dto.BubbleRequestDTO
import com.projects.bubbles.dto.BubbleResponseDTO
import com.projects.bubbles.services.Service
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BubbleViewModel : ViewModel() {
    private val bubbleService = Service.BubbleService

    val bubbleList = MutableLiveData<List<BubbleResponseDTO>>()
    val erro = MutableLiveData<String>()

    init {
        getBubbles()
    }


    fun getBubbles() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = bubbleService.getAllBubbles()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        bubbleList.value = response.body() ?: emptyList()
                        Log.d("BubbleViewModel", "Get bubbles successful: ${response.body()}")
                    } else {
                        val errorMessage =
                            "Erro ao buscar bolhas: ${response.errorBody()?.string()}"
                        erro.value = errorMessage
                        Log.e("BubbleViewModel", errorMessage)
                    }
                }
            } catch (e: Exception) {
                val errorMessage = "Erro ao buscar bolhas: ${e.message}"
                withContext(Dispatchers.Main) {
                    erro.value = errorMessage
                }
                Log.e("BubbleViewModel", errorMessage)
            }
        }
    }

    fun createBubble(bubbleRequest: BubbleRequestDTO) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = bubbleService.createNewBubble(bubbleRequest)
                if (response.isSuccessful) {
                    getBubbles()
                    Log.d("BubbleViewModel", "Create bubble successful: ${response.body()}")
                } else {
                    val errorMessage = "Erro ao criar bolha: ${response.errorBody()?.string()}"
                    withContext(Dispatchers.Main) {
                        erro.value = errorMessage
                    }
                    Log.e("BubbleViewModel", errorMessage)
                }
            } catch (e: Exception) {
                val errorMessage = "Erro ao criar bolha: ${e.message}"
                withContext(Dispatchers.Main) {
                    erro.value = errorMessage
                }
                Log.e("BubbleViewModel", errorMessage)
            }
        }
    }

    fun updateBubble(bubbleId: Int, updatedBubble: BubbleResponseDTO) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = bubbleService.updateBubbleById(bubbleId, updatedBubble)
                if (response.isSuccessful) {
                    getBubbles()
                    Log.d("BubbleViewModel", "Update bubble successful: ${response.body()}")
                } else {
                    val errorMessage = "Erro ao atualizar bolha: ${response.errorBody()?.string()}"
                    withContext(Dispatchers.Main) {
                        erro.value = errorMessage
                    }
                    Log.e("BubbleViewModel", errorMessage)
                }
            } catch (e: Exception) {
                val errorMessage = "Erro ao atualizar bolha: ${e.message}"
                withContext(Dispatchers.Main) {
                    erro.value = errorMessage
                }
                Log.e("BubbleViewModel", errorMessage)
            }
        }
    }

    fun deleteBubble(bubbleId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = bubbleService.deleteBubbleById(bubbleId)
                if (response.isSuccessful) {
                    getBubbles()
                    Log.d("BubbleViewModel", "Delete bubble successful")
                } else {
                    val errorMessage = "Erro ao excluir bolha: ${response.errorBody()?.string()}"
                    withContext(Dispatchers.Main) {
                        erro.value = errorMessage
                    }
                    Log.e("BubbleViewModel", errorMessage)
                }
            } catch (e: Exception) {
                val errorMessage = "Erro ao excluir bolha: ${e.message}"
                withContext(Dispatchers.Main) {
                    erro.value = errorMessage
                }
                Log.e("BubbleViewModel", errorMessage)
            }
        }
    }
}
