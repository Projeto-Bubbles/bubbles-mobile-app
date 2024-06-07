package com.projects.bubbles.services.endpoints

import com.projects.bubbles.dto.BubbleRequestDTO
import com.projects.bubbles.dto.BubbleResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface IBubble {
    @GET("bubbles")
    suspend fun getAllBubbles(): Response<List<BubbleResponseDTO>>

    @POST("bubbles/create")
    suspend fun createNewBubble(@Body newBubbleDTO: BubbleRequestDTO): Response<BubbleResponseDTO>

    @PATCH("bubbles/update/{bubbleId}")
    suspend fun updateBubbleById(@Path("bubbleId") bubbleId: Int, @Body updatedBubbleDTO: BubbleResponseDTO): Response<BubbleResponseDTO>

    @DELETE("bubbles/{bubbleId}")
    suspend fun deleteBubbleById(@Path("bubbleId") bubbleId: Int): Response<Void>
}
