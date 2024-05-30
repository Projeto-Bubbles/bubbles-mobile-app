package com.projects.bubbles.model

import androidx.compose.ui.graphics.painter.Painter

data class EventModel(val bolha: String, val titulo: String, val descricao: String, val endereco: String, val data: String, val imagem: Painter, val icon: Painter) {
}