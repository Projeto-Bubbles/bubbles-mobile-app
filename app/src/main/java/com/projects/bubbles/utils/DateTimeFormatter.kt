//package com.projects.bubbles.utils
//
//import com.github.marlonlom.utilities.timeago.TimeAgo
//import java.text.SimpleDateFormat
//import java.util.Date
//import java.util.Locale
//
//
//fun formatDate(dateTime: String?): String {
//    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS", Locale.getDefault())
//    val date = sdf.parse(dateTime) ?: Date(0)
//    return TimeAgo.using(date.time)
//}
