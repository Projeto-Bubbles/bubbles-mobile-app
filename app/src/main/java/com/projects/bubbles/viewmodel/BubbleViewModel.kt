package com.projects.bubbles.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    val isLoading = MutableLiveData(false)

    init {
        getBubbles()
    }

    fun getBubbles() {
        isLoading.value = true
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

                    isLoading.value = false
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
        isLoading.value = true

        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) { // Executa a chamada de rede em background
                    bubbleService.createNewBubble(bubbleRequest)
                }

                if (response.isSuccessful) {
                    // Atualiza na Main Thread após a chamada de rede
                    withContext(Dispatchers.Main) {
                        bubbleList.value = bubbleService.getAllBubbles().body() ?: emptyList() // Recarrega a lista de bubbles
                        Log.d("BubbleViewModel", "Create bubble successful: ${response.body()}")
                    }
                } else {
                    val errorMessage = "Erro ao criar bolha: ${response.errorBody()?.string()}"
                    erro.postValue(errorMessage)
                    Log.e("BubbleViewModel", errorMessage)
                }
            } catch (e: Exception) {
                val errorMessage = "Erro FATAL bolha: ${e}"
                erro.postValue(errorMessage)
                Log.e("BubbleViewModel", errorMessage)
            } finally {
                isLoading.postValue(false)
            }
        }
    }

    fun updateBubble(bubbleId: Int, updatedBubble: BubbleResponseDTO) {
        isLoading.value = true

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
            } finally {
                isLoading.postValue(false)
            }
        }
    }

    fun deleteBubble(bubbleId: Int) {
        isLoading.value = true

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

                isLoading.postValue(false)
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
