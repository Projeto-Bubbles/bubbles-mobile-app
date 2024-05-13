package com.projects.bubbles.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projects.bubbles.dto.Comment
import com.projects.bubbles.dto.Post
import com.projects.bubbles.dto.PostRequest
import com.projects.bubbles.viewmodel.PostViewModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun CommentBox(
    username: String,
    nickname: String,
    dateTime: String,
    commentContent: String
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFFeeeeee)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Perfil()

                Spacer(modifier = Modifier.width(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = username,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF423f46)
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(
                        text = "@$nickname • $dateTime",
                        fontSize = 10.sp,
                        color = Color(0xFF423f46)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = commentContent,
                fontSize = 12.sp,
                color = Color(0xFF423f46)
            )
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreatePostBox(
    username: String,
    nickname: String,
) {

    val postViewModel = PostViewModel()
    val content = remember { mutableStateOf("") }

    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFFe4e4e4)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Perfil()

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = username,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF423f46)
                )
                Spacer(modifier = Modifier.width(15.dp))


                Text(
                    text = "@$nickname",
                    fontSize = 10.sp,
                    color = Color(0xFF423f46)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Venha interagir com a bolha!",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 16.sp,
                color = Color(0xFF423f46)
            )

            Spacer(modifier = Modifier.height(8.dp))

            PostResponseField(
                onPostCreated = {
                    val newPost =
                        PostRequest(dateTime = LocalDateTime.now(), content = content.value, 1, 1)
                    postViewModel.createPost(newPost)
                }
            )

        }
    }
}

@Composable
fun PostBox(
    username: String,
    nickname: String,
    dateTime: String? = null,
    content: String,
    commentContent: List<Comment>? = emptyList()
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFFe4e4e4)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Perfil()

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = username,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF423f46)
                )
                Spacer(modifier = Modifier.width(15.dp))


                Text(
                    text = "@$nickname • $dateTime",
                    fontSize = 10.sp,
                    color = Color(0xFF423f46)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = content,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 16.sp,
                color = Color(0xFF423f46)
            )

//            Spacer(modifier = Modifier.height(8.dp))
//
//            CommentBox(
//                username = "Paulo Alvares",
//                nickname = "paulinhoAl",
//                dateTime = "2 hours ago",
//                commentContent = "Lorem ipsum dolor sit amet consectetur. In dolor porttitor malesuada sit et. Amet enim iaculis gravida nulla egestas ultrices phasellus consequat. Eget mauris in lacus risus porttitor."
//            )

            Spacer(modifier = Modifier.height(16.dp))

        }
    }
}