package com.projects.bubbles.services.endpoints

import com.projects.bubbles.dto.LoginRequest
import com.projects.bubbles.dto.LoginResponse
import com.projects.bubbles.dto.RegisterRequest
import com.projects.bubbles.dto.RegisterResponse
import com.projects.bubbles.dto.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IAuth {
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("users")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

    @GET("users/email")
    suspend fun getByEmail(@Query("email") email: String): Response<User>
}