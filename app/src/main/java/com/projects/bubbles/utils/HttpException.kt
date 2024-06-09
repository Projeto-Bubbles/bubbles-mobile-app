package com.projects.bubbles.utils

import retrofit2.Response

class HttpException(response: Response<*>) :
    RuntimeException("Erro na requisição HTTP: ${response.code()} - ${response.errorBody()}")
