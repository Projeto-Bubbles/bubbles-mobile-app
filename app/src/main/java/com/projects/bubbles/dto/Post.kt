package com.projects.bubbles.dto

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class Post(
    var idPost:Int,
    var moment:String,
    var contents:String,
    var author: User,
    var bubble: Bubble,
    @SerializedName("comments")
    val comments: List<Comment> = listOf(Comment())
){}