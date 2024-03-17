package com.projects.bubbles.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.projects.bubbles.R
import com.projects.bubbles.components.NormalText
import com.projects.bubbles.components.TextInput
import com.projects.bubbles.components.TitleText
import com.projects.bubbles.ui.theme.Slate100

@Composable
fun SignUpScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
            .background(color = Slate100)
    ) {

        Column {
            TitleText(value = stringResource(id = R.string.sign_up_title))
            NormalText(value = stringResource(id = R.string.sign_up_disclaimer))

            TextInput(
                label = stringResource(id = R.string.sign_up_nickname),
                painterResource(id = R.drawable.user_duotone)
            )

            TextInput(
                label = stringResource(id = R.string.sign_up_email),
                painterResource(id = R.drawable.user_duotone)
            )

            TextInput(
                label = stringResource(id = R.string.sign_up_password),
                painterResource(id = R.drawable.user_duotone)
            )

            TextInput(
                label = stringResource(id = R.string.sign_up_repeat_password),
                painterResource(id = R.drawable.user_duotone)
            )
        }
    }

}

@Preview
@Composable
fun PreviewSignUpScreen() {
    SignUpScreen()
}