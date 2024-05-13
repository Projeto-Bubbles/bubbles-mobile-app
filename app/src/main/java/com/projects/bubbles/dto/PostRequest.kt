package com.projects.bubbles.dto

import java.time.LocalDateTime

data class PostRequest (
    val contents: String,
    val idAuthor: Int,
    val idBubble: Int,
)