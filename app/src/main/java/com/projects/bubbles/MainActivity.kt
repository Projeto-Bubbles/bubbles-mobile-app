package com.projects.bubbles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.projects.bubbles.app.BubblesApp
import com.projects.bubbles.model.Bubble
import com.projects.bubbles.ui.theme.BubblesTheme
import com.projects.bubbles.ui.theme.bubbbleYellow
import com.projects.bubbles.ui.theme.bubbleBlue
import com.projects.bubbles.ui.theme.bubbleGreen
import com.projects.bubbles.ui.theme.bubbleGrey

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val lista = listOf(
                Bubble("música", R.mipmap.music, bubbleBlue),
                Bubble("ciência", R.mipmap.science, bubbleGreen),
                Bubble("tecnologia", R.mipmap.technology, bubbbleYellow),
                Bubble("gastronomia", R.mipmap.culinary, bubbleGrey),
                Bubble("livros", R.mipmap.reading, bubbleGrey),
            )

            BubblesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BubblesApp(rememberNavController())
                }
            }
        }
    }
}