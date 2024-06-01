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