package com.projects.bubbles.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CommentBox(
    userName: String,
    userUsername: String,
    postTime: String,
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
                        text = userName,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF423f46)
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(
                        text = "@$userUsername • $postTime",
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

@Composable
fun PostBox(
    userName: String,
    userUsername: String,
    postTime: String,
    commentContent: String
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

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = userName,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF423f46)
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(
                        text = "@$userUsername • $postTime",
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

            Spacer(modifier = Modifier.height(8.dp))

            CommentBox(
                userName = "Paulo Alvares",
                userUsername = "paulinhoAl",
                postTime = "2 hours ago",
                commentContent = "Lorem ipsum dolor sit amet consectetur. In dolor porttitor malesuada sit et. Amet enim iaculis gravida nulla egestas ultrices phasellus consequat. Eget mauris in lacus risus porttitor."
            )

            Spacer(modifier = Modifier.height(16.dp))

            ResponseField {}
        }
    }
}