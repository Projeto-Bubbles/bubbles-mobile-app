package com.projects.bubbles.dto

import java.time.LocalDateTime

data class Comment (
    val idComment:Int? = null,
    val moment: String? = null,
    val contents: String? = null,
    val author: User? = null
){}