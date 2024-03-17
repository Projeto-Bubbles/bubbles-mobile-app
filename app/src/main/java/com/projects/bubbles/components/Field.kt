package com.projects.bubbles.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import com.projects.bubbles.R
import com.projects.bubbles.ui.theme.Zinc300
import com.projects.bubbles.ui.theme.rounded

@Composable
fun TextInput(label: String, icon: Painter) {
    val text = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        shape = rounded.small,
        label = { Text(text = label) },
        value = text.value,
        onValueChange = { text.value = it },
        keyboardOptions = KeyboardOptions.Default,
        leadingIcon = {
            Icon(painter = icon, contentDescription = label)
        },
    )
}
