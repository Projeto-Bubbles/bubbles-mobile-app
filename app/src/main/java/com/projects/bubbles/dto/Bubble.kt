package com.projects.bubbles.dto

import com.projects.bubbles.dto.enums.Category
import java.time.LocalDate

data class Bubble (
    val idBubble: Int? = null,
    val title: String? = null,
    val explanation: String? = null,
    val creationDate: LocalDate? = null,
    val category: Category? = null,
    val creator: User? = null,
){}

