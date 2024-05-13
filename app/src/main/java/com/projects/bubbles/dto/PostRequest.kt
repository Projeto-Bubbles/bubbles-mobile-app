package com.projects.bubbles.dto

import java.time.LocalDateTime

data class PostRequest (
    val dateTime: LocalDateTime,
    val content: String,
    val idAuthor: Int,
    val idBubble: Int,
)