import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.projects.bubbles.R
import com.projects.bubbles.components.*
import com.projects.bubbles.ui.theme.Slate100
import com.projects.bubbles.ui.theme.Zinc300
import com.projects.bubbles.ui.theme.Zinc350
import com.projects.bubbles.ui.theme.rounded
import com.projects.bubbles.utils.Loading

@Composable
fun SignInScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel = AuthViewModel(),
    context: Context
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val loginResult = authViewModel.loginResult.observeAsState().value
    val isLoading = authViewModel.isLoading.observeAsState().value

  if(loginResult?.token != null){
      navController.navigate("bubbles")
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
                                    icon = painterResource(id = R.drawable.icon_user),
                                    value = email.value,
                                    onValueChange = { email.value = it }
                                )

                                PasswordField(
                                    label = stringResource(id = R.string.sign_up_password),
                                    icon = painterResource(id = R.drawable.icon_password),
                                    value = password.value,
                                    onValueChange = { password.value = it }
                                )

                                Spacer(Modifier.height(20.dp))

                                ButtonComponent(
                                    value = stringResource(id = R.string.sign_in_action_button),
                                    onClick = {
                                        authViewModel.login(email.value, password.value, context)

                                    }
                                )
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

