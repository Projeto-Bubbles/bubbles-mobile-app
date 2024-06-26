package com.projects.bubbles.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.projects.bubbles.R
import com.projects.bubbles.dto.PostRequest
import com.projects.bubbles.ui.theme.Zinc300
import com.projects.bubbles.ui.theme.rounded
import com.projects.bubbles.viewmodel.PostViewModel

@Composable
fun TextField(
    label: String,
    icon: Painter,
    modifier: Modifier = Modifier,
    shape: Shape = rounded.small,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    OutlinedTextField(modifier = modifier.fillMaxWidth(),
        shape = shape,
        label = { Text(text = label) },
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        leadingIcon = {
            Icon(painter = icon, contentDescription = label)
        })
}

@Composable
fun PasswordField(
    label: String,
    icon: Painter,
    modifier: Modifier = Modifier,
    shape: Shape = rounded.small,
    value: String,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation = PasswordVisualTransformation(),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    val passwordVisible = remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        shape = shape,
        label = { Text(text = label) },
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = Color.Gray,
            focusedBorderColor = Color.Gray,
            focusedLabelColor = Color.Gray,
        ),
        keyboardOptions = keyboardOptions.copy(keyboardType = KeyboardType.Password),
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(painter = icon, contentDescription = null)
        },
        trailingIcon = {
            IconButton(
                onClick = { passwordVisible.value = !passwordVisible.value },
                modifier = Modifier.size(50.dp)
            ) {
                val iconImage = if (passwordVisible.value) {
                    Icon(
                        painter = painterResource(id = R.mipmap.visibility),
                        contentDescription = "Senha visível"
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.mipmap.visibility_off),
                        contentDescription = "Senha invisível"
                    )
                }
            }
        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else visualTransformation
    )
}

@Composable
fun PostResponseField(postViewModel: PostViewModel = PostViewModel()) {
    var content = remember { mutableStateOf("") }

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
            textStyle = TextStyle.Default.copy(color = Color.Black),
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
                        PostRequest(contents = content.value, 1, 1)
                    postViewModel.createPost(newPost)

                    content.value = ""
                }
            )
        )
    }
}


@Composable
fun Search(placeholder: String, onValueChange: (String) -> Unit) {
    var searchedItem by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .width(255.dp)
            .height(35.dp)
            .background(
                color = Zinc300,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search Icon",
            tint = Color.Black,
            modifier = Modifier
                .size(30.dp),
        )

        Spacer(modifier = Modifier.width(8.dp))

        BasicTextField(
            value = searchedItem,
            onValueChange = {
                searchedItem = it
                onValueChange(it)
            },
            textStyle = TextStyle.Default.copy(color = Color.Black),
            modifier = Modifier.fillMaxSize(),
            decorationBox = { innerTextField ->
                Box(modifier = Modifier.fillMaxSize()) {
                    if (searchedItem.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = Color.Gray,
                            style = TextStyle.Default,
                            modifier = Modifier.padding(horizontal = 2.dp)
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}

