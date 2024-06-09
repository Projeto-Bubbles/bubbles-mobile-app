package com.projects.bubbles.dto

import java.time.LocalDateTime

sealed class Event(
    open val idEvent: Int?,
    open val title: String,
    open val dateTime: String,
    open val duration: Int,
    open val organizer: User,
    open val bubble: Bubble,
) {
    class InPerson(
        override val idEvent: Int?,
        override val title: String,
        override val dateTime: String,
        override val duration: Int,
        override val organizer: User,
        override val bubble: Bubble,
        val publicPlace: Boolean,
        val peopleCapacity: Int?,
        val address: Address
    ) : Event(idEvent, title, dateTime, duration, organizer, bubble)

    class Online(
        override val idEvent: Int?,
        override val title: String,
        override val dateTime: String,
        override val duration: Int,
        override val organizer: User,
        override val bubble: Bubble,
        val platform: String,
        val link: String
    ) : Event(idEvent, title, dateTime, duration, organizer, bubble)
}