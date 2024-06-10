package com.projects.bubbles.dto

data class LoginRequest(
    val email: String,
    val password: String
)

data class RegisterRequest (
    val username: String,
    val nickname: String,
    val email: String,
    val password: String,
    val cpf: String
)

data class LoginResponse(
    val token: String // Suponha que a resposta de login contenha um token de autenticação
)

data class RegisterResponse(
    val message: String // Suponha que a resposta de registro contenha uma mensagem de confirmação
)
