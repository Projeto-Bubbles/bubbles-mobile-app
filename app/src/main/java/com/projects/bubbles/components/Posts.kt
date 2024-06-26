package com.projects.bubbles.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projects.bubbles.R
import com.projects.bubbles.dto.Post
import com.projects.bubbles.dto.PostRequest
import com.projects.bubbles.dto.User
import com.projects.bubbles.utils.AnimationSlider
import com.projects.bubbles.viewmodel.PostViewModel
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
    user: User,
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
                    text = user.username,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF423f46)
                )

                Spacer(modifier = Modifier.width(8.dp))


                Text(
                    text = "@${user.nickname}",
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
                            val newPost =
                                PostRequest(contents = content.value, user.idUser!!, 2)

                            postViewModel.createPost(newPost)

                            content.value = ""
                        }
                    )
                )
            }

        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
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

    val localDateTime = if (dateTime != null) {
        LocalDateTime.parse(dateTime)
    } else {
        null
    }

    val hours = localDateTime?.format(DateTimeFormatter.ofPattern("HH")) ?: ""
    val date = localDateTime?.format(DateTimeFormatter.ofPattern("dd/MM")) ?: ""

    AnimationSlider {
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
                        text = "@$nickname   •   ${hours}h   •   $date",
                        fontSize = 10.sp,
                        color = Color(0xFF423f46)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        if (post.author?.idUser == userState.idUser) {
                            DeleteButton(onDelete = { postViewModel.deletePost(post.idPost!!) })

                            Spacer(modifier = Modifier.width(4.dp))

                            EditButton(onEdit = { showEditDialog.value = true })
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = content,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 16.sp,
                    color = Color(0xFF423f46)
                )

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
                value = editedContent.value ?: initialContent,
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


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PostBoxPreview() {
    val postViewModel = PostViewModel() // Cria uma instância do ViewModel

    // Simula um objeto User
    val userState = User(1, "Nome", "Sobrenome", "nome@email.com")

    // Simula um objeto Post
    val post = Post(
        idPost = 15,
        author = userState,
        contents = "Oi",
        moment = LocalDateTime.now().toString(),
    )

    PostBox(
        username = "Nome do Usuário",
        nickname = "nickname",
        dateTime = post.moment,
        content = "Este é um exemplo de conteúdo de um post.",
        postViewModel = postViewModel, // Passa o ViewModel
        post = post,
        onEditClick = {}, // Função vazia, pois não estamos testando a edição
        userState = userState // Passa o estado do usuário
    )
}