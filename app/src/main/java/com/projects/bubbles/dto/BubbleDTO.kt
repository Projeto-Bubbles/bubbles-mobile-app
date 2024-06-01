package com.projects.bubbles.dto

import java.time.LocalDate

data class BubbleResponseDTO(
    val idBubble: Int?,
    val title: String?,
    val explanation: String?,
    val creationDate: LocalDate?,
    val category: Category?,
    val creator: UserInfoDTO?
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

// Definição do enum Category (substitua pelos valores reais da sua API)
enum class Category {
    MUSICA, CIENCIA, TECNOLOGIA, GASTRONOMIA, LIVROS, ESPORTES, ARTE, GAMES
}