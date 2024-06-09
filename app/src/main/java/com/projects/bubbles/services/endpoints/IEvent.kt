package com.projects.bubbles.services.endpoints

import com.projects.bubbles.dto.BubbleResponseDTO
import com.projects.bubbles.dto.EventInPersonRequestDTO
import com.projects.bubbles.dto.EventOnlineRequestDTO
import com.projects.bubbles.dto.EventResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface IEvent {
    @GET("events")
    suspend fun getAllEvents(): Response<List<EventResponseDTO>>

    @POST("events/inPerson")
    suspend fun createInPersonEvent(@Body eventRequest: EventInPersonRequestDTO): Response<EventResponseDTO>

    @POST("events/online")
    suspend fun createOnlineEvent(@Body eventRequest: EventOnlineRequestDTO): Response<EventResponseDTO>

}