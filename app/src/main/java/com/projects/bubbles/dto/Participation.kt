package com.projects.bubbles.dto

data class Participation(
    val idParticipant: Int? = null,
    val participant: User? = null,
    val event: Event? = null
) {}