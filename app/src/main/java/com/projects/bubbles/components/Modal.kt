package com.projects.bubbles.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.projects.bubbles.dto.BubbleRequestDTO
import com.projects.bubbles.dto.enums.Category
import com.projects.bubbles.ui.theme.Zinc350
import com.projects.bubbles.ui.theme.rounded
import com.projects.bubbles.viewmodel.BubbleViewModel

@Composable
fun Modal(
    title: String,
    isVisible: Boolean,
    onClose: () -> Unit,
    content: @Composable () -> Unit
) {
    if (isVisible) {
        Dialog(
            onDismissRequest = onClose,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(rounded.medium)
                    .background(Zinc350)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Normal)

                        IconButton(modifier = Modifier.offset(x = 16.dp), onClick = onClose) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Fechar modal",
                            )
                        }
                    }

                    content()
                }
            }
        }
    }
}


@Composable
fun CreateBubbleModal(
    viewModel: BubbleViewModel,
    onClose: () -> Unit,
    idUser: Int
) {
    var title by remember { mutableStateOf("") }
    var category by remember { mutableStateOf<Category?>(null) }
    var explanation by remember { mutableStateOf("") }
//    var imageUri by remember { mutableStateOf<Uri?>(null) }

    Modal(title = "Criar bolha", isVisible = true, onClose = onClose) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
//            // Campo para imagem (pode ser um botão para selecionar imagem da galeria)
//            OutlinedButton(
//                onClick = { /* Lógica para selecionar imagem */ },
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text("Selecionar Imagem")
//            }

            Spacer(modifier = Modifier.height(16.dp))

            // Input para o título da bolha
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Nome da bolha") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            var expanded by remember { mutableStateOf(false) }
            OutlinedTextField(
                value = category?.toCapitalizedPortuguese() ?: "",
                onValueChange = { },
                label = { Text("Categoria") },
                trailingIcon = {
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = null,
                        Modifier.clickable { expanded = !expanded })
                },
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                Category.values().forEach { cat ->
                    DropdownMenuItem(
                        text = { Text(cat.toCapitalizedPortuguese()) },
                        onClick = {
                            category = cat // Define o valor do enum
                            expanded = false
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Textarea para a descrição
            OutlinedTextField(
                value = explanation,
                onValueChange = { explanation = it },
                label = { Text("Descrição") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botão para criar a bolha
            Button(
                onClick = {
                    viewModel.createBubble(
                        BubbleRequestDTO(title, explanation, category!!, idUser)
                    )
                    onClose() // Fecha o modal após criar a bolha
                },
                enabled = title.isNotBlank() && category != null && explanation.isNotBlank(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Criar Bolha")
            }
        }
    }
}

fun Category.toCapitalizedPortuguese(): String {
    return when (this) {
        Category.SPORTS -> "Esportes"
        Category.MUSIC -> "Música"
        Category.GAME -> "Jogos"
        Category.ART -> "Arte"
        Category.TECHNOLOGY -> "Tecnologia"
        Category.SCIENCE -> "Ciência"
        Category.CULINARY -> "Culinária"
        Category.READING -> "Leitura"
    }
}

@Preview
@Composable
fun PreviewModal() {

}
