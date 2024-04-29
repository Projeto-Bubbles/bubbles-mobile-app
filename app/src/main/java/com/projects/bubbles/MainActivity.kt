package com.projects.bubbles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.projects.bubbles.model.Bubble
import com.projects.bubbles.screens.JoinBubble
import com.projects.bubbles.screens.SelectBubble
import com.projects.bubbles.screens.SelectCategory
import com.projects.bubbles.screens.SignUpScreen
import com.projects.bubbles.ui.theme.BubblesTheme
import com.projects.bubbles.ui.theme.bubbleBlue
import com.projects.bubbles.ui.theme.bubbleGreen
import com.projects.bubbles.ui.theme.bubbleGrey
import com.projects.bubbles.ui.theme.bubblePurple
import com.projects.bubbles.ui.theme.bubbleYellow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val lista = listOf(
                Bubble("música", R.mipmap.music, bubbleBlue),
                Bubble("ciência", R.mipmap.science, bubbleGreen),
                Bubble("tecnologia", R.mipmap.technology, bubbleYellow),
                Bubble("gastronomia", R.mipmap.culinary, bubbleGrey),
                Bubble("livros", R.mipmap.reading, bubbleGrey),
            )
            BubblesTheme {
                JoinBubble(lista)
            }

        }
    }
}
