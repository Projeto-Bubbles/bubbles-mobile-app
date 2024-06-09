package com.projects.bubbles.dto

data class EventResponseDTO(
    val idEvent: Int,
    val title: String,
    val dateTime: String,
    val duration: Int,
    val organizer: UserInfoDTO,
    val bubble: BubbleResponseDTO,
    val publicPlace: Boolean? = null,
    val peopleCapacity: Int? = null,
    val address: Address? = null,
    val platform: String? = null,
    val link: String? = null
)

data class EventOnlineRequestDTO(
    val title: String,
    val dateTime: String,
    val duration: Int,
    val idCreator: Int,
    val idBubble: Int,
    val link: String,
    val platform: String
)

data class EventInPersonRequestDTO(
    val title: String,
    val dateTime: String,
    val duration: Int,
    val idCreator: Int,
    val idBubble: Int,
    val publicPlace: Boolean,
    val peopleCapacity: Int?,
    val address: Address
)

interface EventRequest {
    val title: String
    val dateTime: String
    val duration: Int
    val idCreator: Int
    val idBubble: Int
}

