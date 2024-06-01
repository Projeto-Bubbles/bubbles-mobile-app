package com.projects.bubbles.components

import android.os.Build
import android.provider.CalendarContract.Colors
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.twotone.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.projects.bubbles.R
import com.projects.bubbles.dto.Comment
import com.projects.bubbles.dto.Post
import com.projects.bubbles.dto.PostRequest
import com.projects.bubbles.dto.User
import com.projects.bubbles.viewmodel.PostViewModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle

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
    postViewModel: PostViewModel
) {

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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.mipmap.response_arrow),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                BasicTextField(
                    value = content.value,
                    onValueChange = { content.value = it },
                    textStyle = androidx.compose.ui.text.TextStyle.Default.copy(color = Color.Black),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            Log.d("FIELD", "RODEI AQUI")


                            val newPost =
                                PostRequest(contents = content.value, 1, 2)
                            postViewModel.createPost(newPost)

                            content.value = ""
                        }
                    )
                )
            }

        }
    }
}

@Composable
fun PostBox(
    username: String,
    nickname: String,
    dateTime: String? = null,
    content: String,
    postViewModel: PostViewModel,
    post: Post,
    onEditClick: (Post) -> Unit,
    userState: User
) {
    var showEditDialog = remember { mutableStateOf(false) }
    var editedContent = remember { mutableStateOf(content) }

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
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Perfil()

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = username,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF423f46)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "@$nickname • $dateTime",
                    fontSize = 10.sp,
                    color = Color(0xFF423f46)
                )

                if (post.author?.idUser == userState.idUser) {
                    Button(
                        onClick = { postViewModel.deletePost(post.idPost!!) },
                        modifier = Modifier
                            .size(16.dp)
                            .clip(RoundedCornerShape(2.dp)),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Icon(
                            Icons.Filled.Delete,
                            contentDescription = "Deletar post",
                            tint = Color.White
                        )
                    }

                    Button(
                        onClick = { showEditDialog.value },
                        modifier = Modifier
                            .size(16.dp)
                            .clip(RoundedCornerShape(2.dp)),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                    ) {
                        Icon(
                            Icons.Filled.Edit,
                            contentDescription = "Editar post",
                            tint = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
            }
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


                if (showEditDialog.value) {
                    EditPostDialog(
                        initialContent = content,
                        onDismiss = { showEditDialog.value = false },
                        onConfirm = { newContent ->
                            postViewModel.updatePost(post.idPost!!, newContent)
                            showEditDialog.value = false
                        }
                    )
                }
            }
        }
    }

@Composable
fun EditPostDialog(
    initialContent: String,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    val editedContent = remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Editar Post") },
        text = {
            OutlinedTextField(
                value = editedContent.value,
                onValueChange = { editedContent.value = it },
                label = { Text(text = "Novo Conteúdo") }
            )
        },
        confirmButton = {
            Button(onClick = { onConfirm(editedContent.value) }) {
                Text("Salvar")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}