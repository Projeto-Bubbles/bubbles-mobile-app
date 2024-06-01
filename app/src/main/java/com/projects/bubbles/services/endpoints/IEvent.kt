package com.projects.bubbles.services.endpoints

import com.projects.bubbles.dto.BubbleResponseDTO
import com.projects.bubbles.dto.EventResponseDTO
import retrofit2.Response
import retrofit2.http.GET

interface IEvent {
    @GET("events")
    suspend fun getAllEvents(): Response<List<EventResponseDTO>>

}