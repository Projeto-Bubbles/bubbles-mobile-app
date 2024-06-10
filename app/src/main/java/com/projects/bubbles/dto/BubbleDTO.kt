package com.projects.bubbles.dto

import com.projects.bubbles.dto.enums.Category
import java.time.LocalDate

data class BubbleResponseDTO(
    val idBubble: Int?,
    val title: String?,
    val explanation: String?,
    val creationDate: String,
    val category: Category?,
    val creator: UserInfoDTO?,
    val image: String? = null
)

data class BubbleRequestDTO(
    val title: String,
    val explanation: String,
    val category: Category,
    val creator: Int
)

data class UserInfoDTO(
    val idUser: Int?,
    val username: String?,
    val nickname: String?,
    val email: String?
)
