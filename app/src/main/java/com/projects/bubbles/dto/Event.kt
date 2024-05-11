package com.projects.bubbles.dto

import java.time.LocalDateTime

data class Event (
    val idEvent: Int? = null,
    val title: String? = null,
    val moment: LocalDateTime? = null,
    val duration: Int? = null,
    val organizer: User? = null,
    val bubble: Bubble? = null,
) {
}