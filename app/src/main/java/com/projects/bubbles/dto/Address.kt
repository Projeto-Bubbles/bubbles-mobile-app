package com.projects.bubbles.dto

data class Address(
    val idAddress: Int? = null,
    val cep: String? = null ,
    val estate: String? = null,
    val city: String? = null,
    val neighborhood: String? = null,
    val street: String? = null,
    val houseNumber: String? = null
) {
}