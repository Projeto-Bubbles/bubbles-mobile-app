package com.projects.bubbles.dto

data class EventResponseDTO(
    val idEvent: Int,
    val title: String,
    val dateTime: String,
    val duration: Int,
    val organizer: UserInfoDTO,
    val bubble: BubbleResponseDTO
)

data class EventRequestDTO(
    val title: String,
    val dateTime: String,
    val duration: Int,
    val idCreator: Int,
    val idBubble: Int
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

data class EventOnlineRequestDTO(
    val title: String,
    val dateTime: String,
    val duration: Int,
    val idCreator: Int,
    val idBubble: Int,
    val platform: String,
    val link: String
)

