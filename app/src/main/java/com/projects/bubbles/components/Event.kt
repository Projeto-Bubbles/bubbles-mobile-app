package com.projects.bubbles.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projects.bubbles.R
import com.projects.bubbles.dto.Address
import com.projects.bubbles.dto.BubbleResponseDTO
import com.projects.bubbles.dto.EventResponseDTO
import com.projects.bubbles.dto.UserInfoDTO
import com.projects.bubbles.dto.enums.Category
import com.projects.bubbles.dto.getCategories
import com.projects.bubbles.ui.theme.Green200
import com.projects.bubbles.ui.theme.Green500
import com.projects.bubbles.ui.theme.Slate400
import com.projects.bubbles.ui.theme.Zinc350
import com.projects.bubbles.ui.theme.Zinc500
import com.projects.bubbles.ui.theme.rounded
import com.projects.bubbles.utils.AnimationSlider
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalLayoutApi::class, ExperimentalAnimationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EventCard(
    event: EventResponseDTO, image: Painter,
    onPresenceConfirmed: () -> Unit = {}
) {
    val context = LocalContext.current
    val bubble = getCategories().find { it.category == event.bubble.category }

    var isClicked = remember { mutableStateOf(false) }

    val buttonColor by animateColorAsState(
        targetValue = if (isClicked.value) Green500 else Zinc500,
        animationSpec = tween(durationMillis = 500), label = ""
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .weight(0.35f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .weight(0.6f)
                    .fillMaxHeight()
                    .padding(0.dp, 16.dp, 8.dp, 16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(0.95f),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (bubble != null) {
                            BubbleTag(categoryData = bubble, fixed = true, onClick = {})
                        }

                        Text(
                            text = if (event.platform != null) "online" else "presencial",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Zinc500
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Column {
                        Text(
                            text = event.title,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 18.sp,
                            fontSize = 20.sp,
                        )

                        Text(
                            text = event.bubble.title!!,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                        )

                    }
                }

                FlowRow(
                    modifier = Modifier.fillMaxWidth(0.95f),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (event.platform != null) {
                        EventInfo(icon = R.drawable.icon_link, text = "Link")
                        EventInfo(icon = R.drawable.icon_platform, text = event.platform)
                    } else {
                        val address = event.address

                        EventInfo(
                            icon = R.drawable.icon_location,
                            text = "${address?.street}, ${address?.houseNumber}" ?: ""
                        )
                    }

                    val formattedDateTime =
                        LocalDateTime.parse(event.dateTime, DateTimeFormatter.ISO_DATE_TIME)
                    EventInfo(
                        icon = R.drawable.icon_calendar,
                        text = formattedDateTime.format(DateTimeFormatter.ofPattern("dd/MM"))
                    )
                    EventInfo(
                        icon = R.drawable.icon_hour,
                        text = formattedDateTime.format(DateTimeFormatter.ofPattern("HH:mm"))
                    )
                }

                Button(
                    onClick = {
                        isClicked.value = !isClicked.value
                        onPresenceConfirmed()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
                ) {
                    AnimatedVisibility(
                        visible = isClicked.value,
                        enter = fadeIn(
                            initialAlpha = 0.4f
                        ),
                        exit = fadeOut(
                            animationSpec = tween(durationMillis = 250)
                        )
                    ) {
                        Icon(
                            modifier = Modifier.fillMaxWidth(),
                            painter = painterResource(id = R.drawable.icon_marked),
                            contentDescription = "Presença confirmada",
                            tint = Color.White
                        )
                    }

                    AnimatedVisibility( // Use AnimatedVisibility para a transição
                        visible = !isClicked.value,
                        enter = fadeIn(
                            initialAlpha = 0.4f
                        ),
                        exit = fadeOut(
                            animationSpec = tween(durationMillis = 250)
                        )
                    ) {
                        Text(text = "MARCAR PRESENÇA", color = Color.White)
                    }
                }
            }
        }
    }
}


@Composable
fun EventInfo(icon: Int, text: String) {
    Row(
        modifier = Modifier
            .clip(shape = rounded.large)
            .background(Zinc350)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            modifier = Modifier.size(12.dp),
            painter = painterResource(id = icon),
            contentDescription = null,
        )

        Spacer(modifier = Modifier.width(6.dp))

        Text(
            text = text,
            fontSize = 12.sp,
            lineHeight = 12.sp
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewEvent() {
    val event = EventResponseDTO(
        idEvent = 1,
        title = "Evento de Teste",
        dateTime = LocalDateTime.now().toString(),
        duration = 120,
        organizer = UserInfoDTO(1, "Nome do Organizador", "organizador", "organizador@email.com"),
        bubble = BubbleResponseDTO(
            idBubble = 1,
            title = "Bolha de Teste",
            explanation = "Descrição da bolha de teste",
            creationDate = LocalDateTime.now().minusDays(5).format(DateTimeFormatter.ISO_DATE_TIME),
            category = Category.SPORTS,
            creator = UserInfoDTO(2, "Criador da Bolha", "criador", "criador@email.com")
        ),
        publicPlace = true,
        peopleCapacity = 50,
        address = Address(
            1,
            "12345-678",
            "SP",
            "São Paulo",
            "Bairro",
            "Rua do Caralho da POrra da Buceta do Caralho Receba",
            "123"
        )
    )

    EventCard(
        event = event,
        image = painterResource(id = R.mipmap.event_bg_2),
    )
}





