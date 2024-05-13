import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.projects.bubbles.R
import com.projects.bubbles.Screen
import com.projects.bubbles.components.*
import com.projects.bubbles.dto.LoginResponse
import com.projects.bubbles.ui.theme.Slate100
import com.projects.bubbles.ui.theme.Zinc300
import com.projects.bubbles.ui.theme.Zinc350
import com.projects.bubbles.ui.theme.rounded

@Composable
fun SignInScreen(
    navController: NavController,
    authViewModel: AuthViewModel = AuthViewModel(),
    onLoginSuccess: () -> Unit
) {
    var email = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }
    val loginResult = authViewModel.loginResult.observeAsState()

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
                    .height(200.dp),
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
            )
            {
                Column {

                    Column(
                        Modifier.padding(32.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        ArrowRight()
                        TitleText(value = stringResource(id = R.string.sign_in_title))
                        NormalText(value = stringResource(id = R.string.sign_in_disclaimer))
                    }

                    Box(
                        Modifier
                            .fillMaxWidth()
                            .clip(rounded.small)
                            .background(Zinc350)
                            .padding(32.dp)
                    ) {
                        Column {
                            TextField(
                                label = stringResource(id = R.string.sign_up_email),
                                icon = painterResource(id = R.drawable.user_duotone),
                                value = email.value,
                                onValueChange = { email.value = it }
                            )

                            PasswordField(
                                label = stringResource(id = R.string.sign_up_password),
                                icon = painterResource(id = R.drawable.user_duotone),
                                value = password.value,
                                onValueChange = { password.value = it }
                            )

                            Spacer(Modifier.height(20.dp))

                            ButtonComponent(
                                value = stringResource(id = R.string.sign_in_action_button),
                                onClick = {
                                    if (authViewModel.login(email.value, password.value)) {
                                        onLoginSuccess.invoke()
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewSignInScreen() {
    SignInScreen(navController = rememberNavController()) {}
}
