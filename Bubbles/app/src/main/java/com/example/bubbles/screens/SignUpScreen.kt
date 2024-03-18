package com.example.bubbles.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bubbles.components.ButtonComponent
import com.example.bubbles.components.HeadingTextComponent
import com.example.bubbles.components.LogoComponent
import com.example.bubbles.components.MyTextField
import com.example.bubbles.components.PasswordTextField
import com.example.bubbles.components.SubTextComponent
import com.example.bubbles.components.SubTextComponent2
import com.example.bubbles.components.circle

@Composable
fun SignUpScreen() {

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()
            .offset(y = 60.dp)) // Desce os elementos para o meio da tela
        {
            LogoComponent()
            Spacer(modifier = Modifier.height(40.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .height(400.dp)
                    .background(Color(0.8941f, 0.8941f, 0.8941f))
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    circle()
                    Spacer(modifier = Modifier.height(20.dp))
                    HeadingTextComponent(value = "Bem vindo de volta!")
                    SubTextComponent(value = "Ainda não furou a bolha ? Cadastre-se")
                    Spacer(modifier = Modifier.height(30.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0.8392f, 0.8392f, 0.8392f))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(20.dp)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center
                            ) {
                                MyTextField(labelValue = "Email", icon = Icons.Default.Email)
                                PasswordTextField(labelValue = "Senha", icon = Icons.Default.Lock)
                                Spacer(modifier = Modifier.height(10.dp))
                                SubTextComponent2(value = "Esqueceu a senha ? Recuperar")
                                Spacer(modifier = Modifier.height(20.dp))
                                ButtonComponent(value = "Entrar")
                            }
                        }

                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun DefaultPreviewOfSignUpScreen() {
    SignUpScreen()
}