package com.projects.bubbles.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projects.bubbles.R
import com.projects.bubbles.ui.theme.Red300
import com.projects.bubbles.ui.theme.Zinc500
import com.projects.bubbles.ui.theme.Zinc700
import com.projects.bubbles.ui.theme.rounded
import com.projects.bubbles.utils.AnimationSlider


@Composable
fun NormalText(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth(),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = Zinc700
    )
}

@Composable
fun TitleText(value: String, fontWeight:FontWeight = FontWeight.Bold) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 10.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontStyle = FontStyle.Normal
        ),
        color = Zinc700
    )
}

@Composable
fun SubtitleText(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 10.dp),
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color = Zinc500
    )
}

@Composable
fun NotFound(
    errorMessage: String,
    suggestion: String
) {
    AnimationSlider {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                Modifier
                    .size(32.dp)
                    .clip(shape = rounded.small)
                    .background(Red300),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(id = R.drawable.not_found),
                    contentDescription = errorMessage,
                    tint = Zinc700
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = errorMessage,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )

            Text(
                text = suggestion,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
