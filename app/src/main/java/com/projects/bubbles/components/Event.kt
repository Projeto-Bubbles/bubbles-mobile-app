package com.projects.bubbles.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projects.bubbles.R
import com.projects.bubbles.dto.EventResponseDTO
import com.projects.bubbles.dto.getCategories
import com.projects.bubbles.ui.theme.Blue200
import com.projects.bubbles.ui.theme.Green200
import com.projects.bubbles.ui.theme.Slate800
import com.projects.bubbles.ui.theme.Zinc300
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun EventCard(event: EventResponseDTO, image: Painter, onJoinClick: () -> Unit) {
    val context = LocalContext.current
    val bubble = getCategories().find { it.category == event.bubble.category }

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
                    .fillMaxHeight().padding(0.dp, 16.dp, 8.dp, 16.dp ),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (bubble != null) {
                        BubbleTag(categoryData = bubble, fixed = true, onClick = {})
                    }
                }

                Column(Modifier.height(150.dp).fillMaxWidth(), verticalArrangement = Arrangement.SpaceBetween) {
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

                Button(
                    onClick = onJoinClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("MARCAR PRESENÇA")
                }
            }
        }
    }
}


//    @Composable
//    fun IconText(icon: Painter, text: String) {
//        Row(verticalAlignment = Alignment.CenterVertically) {
//            Icon(
//                painterResource(id = icon),
//                contentDescription = null,
//            )
//            Spacer(modifier = Modifier.width(4.dp))
//            Text(
//                text = text,
//            )
//        }
//    }

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun formatDate(date: LocalDateTime?, pattern: String): String {
    return if (date != null) {
        DateTimeFormatter.ofPattern(pattern).format(date)
    } else {
        ""
    }
}


