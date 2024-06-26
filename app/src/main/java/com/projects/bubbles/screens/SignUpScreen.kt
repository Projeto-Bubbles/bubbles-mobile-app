package com.projects.bubbles.screens

import AuthViewModel
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.projects.bubbles.R
import com.projects.bubbles.components.ArrowRight
import com.projects.bubbles.components.BubbleLogo
import com.projects.bubbles.components.ButtonComponent
import com.projects.bubbles.components.NormalText
import com.projects.bubbles.components.PasswordField
import com.projects.bubbles.components.SubtitleText
import com.projects.bubbles.components.TextField
import com.projects.bubbles.components.TitleText
import com.projects.bubbles.dto.RegisterRequest
import com.projects.bubbles.ui.theme.Slate100
import com.projects.bubbles.ui.theme.Zinc300
import com.projects.bubbles.ui.theme.Zinc350
import com.projects.bubbles.ui.theme.rounded
import com.projects.bubbles.utils.Loading

@Composable
fun SignUpScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel = AuthViewModel(),
) {
    var name = remember { mutableStateOf("") }
    var nickname = remember { mutableStateOf("") }
    var email = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }
    var repeatPassword = remember { mutableStateOf("") }

    val registerResult = authViewModel.registerResult.observeAsState().value
    val isLoading = authViewModel.isLoading.observeAsState().value

    if (registerResult != null) {
        navController.navigate("login")
    }

    Box(Modifier.fillMaxWidth()) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .background(color = Slate100)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(190.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    BubbleLogo()
                }

                Box(
                    Modifier
                        .fillMaxWidth()
                        .clip(rounded.small)
                        .background(Zinc300)
                ) {
                    Column {

                        Column(
                            Modifier.padding(32.dp), verticalArrangement = Arrangement.Center
                        ) {
                            ArrowRight()
                            TitleText(value = stringResource(id = R.string.sign_up_title))
                            NormalText(value = stringResource(id = R.string.sign_up_disclaimer))
                        }

                        Box(
                            Modifier
                                .fillMaxWidth()
                                .clip(rounded.small)
                                .background(Zinc350)
                                .padding(32.dp)
                        ) {
                            Column(
                                modifier = Modifier.height(450.dp)
                            ) {
                                SubtitleText(value = stringResource(id = R.string.sign_up_account_infos))

                                Spacer(Modifier.height(20.dp))

                                TextField(label = stringResource(id = R.string.sign_up_name),
                                    icon = painterResource(id = R.drawable.icon_user),
                                    value = name.value,
                                    onValueChange = { name.value = it })

                                TextField(label = stringResource(id = R.string.sign_up_nickname),
                                    icon = painterResource(id = R.drawable.icon_nickname),
                                    value = nickname.value,
                                    onValueChange = { nickname.value = it })

                                TextField(label = stringResource(id = R.string.sign_up_email),
                                    icon = painterResource(id = R.drawable.icon_email),
                                    value = email.value,
                                    onValueChange = { email.value = it })


                                PasswordField(label = stringResource(id = R.string.sign_up_password),
                                    icon = painterResource(id = R.drawable.icon_password),
                                    value = password.value,
                                    onValueChange = { password.value = it })

                                PasswordField(label = stringResource(id = R.string.sign_up_repeat_password),
                                    icon = painterResource(id = R.drawable.icon_password),
                                    value = repeatPassword.value,
                                    onValueChange = { repeatPassword.value = it })


                                Spacer(Modifier.height(20.dp))

                                ButtonComponent(value = stringResource(id = R.string.sign_up_action_button),
                                    onClick = {
                                        val registerRequest = RegisterRequest(
                                            username = name.value,
                                            nickname = nickname.value,
                                            email = email.value,
                                            password = password.value,
                                            cpf = "46292412806"
                                        )
                                        authViewModel.register(registerRequest)
                                    })
                            }
                        }

                    }

                }
            }

            if (isLoading == true) {
                Loading()
            }
        }
    }
}
