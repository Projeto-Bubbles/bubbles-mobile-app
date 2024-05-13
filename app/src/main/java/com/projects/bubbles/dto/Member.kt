package com.projects.bubbles.dto

import com.google.gson.annotations.SerializedName

data class Member (
    val idMember: Int? = null,
    @SerializedName("users")
    val fkUser: List<User>,
    @SerializedName("bubbles")
    val fkBubble: List<Bubble> = listOf(Bubble())
){}