package com.projects.bubbles.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.projects.bubbles.dto.Address
import com.projects.bubbles.dto.BubbleRequestDTO
import com.projects.bubbles.dto.BubbleResponseDTO
import com.projects.bubbles.dto.EventInPersonRequestDTO
import com.projects.bubbles.dto.EventOnlineRequestDTO
import com.projects.bubbles.dto.enums.Category
import com.projects.bubbles.ui.theme.Zinc350
import com.projects.bubbles.ui.theme.rounded
import com.projects.bubbles.viewmodel.BubbleViewModel
import com.projects.bubbles.viewmodel.EventViewModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

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


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun EditBubbleModal(
    bubble: BubbleResponseDTO,
    viewModel: BubbleViewModel,
    onClose: () -> Unit
) {
    var editedTitle by remember(bubble.title) { mutableStateOf(bubble.title ?: "") }
    var editedExplanation by remember(bubble.explanation) {
        mutableStateOf(
            bubble.explanation ?: ""
        )
    }

    Modal(title = "Editar Bolha", isVisible = true, onClose = onClose) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = editedTitle,
                onValueChange = { editedTitle = it },
                label = { Text("Nome da bolha") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = editedExplanation,
                onValueChange = { editedExplanation = it },
                label = { Text("Descrição") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = onClose) {
                    Text("Cancelar")
                }

                Button(
                    onClick = {
                        viewModel.updateBubble(
                            bubble.idBubble!!,
                            BubbleResponseDTO(
                                idBubble = bubble.idBubble,
                                title = editedTitle,
                                explanation = editedExplanation,
                                category = bubble.category,
                                creationDate = bubble.creationDate,
                                creator = bubble.creator
//                                image = bubble.image,
                            )
                        )
                        onClose()
                    },
                    enabled = editedTitle.isNotBlank() && editedExplanation.isNotBlank()
                ) {
                    Text("Salvar")
                }
            }
        }
    }
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
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
            Spacer(modifier = Modifier.height(16.dp))

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
                            category = cat
                            expanded = false
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = explanation,
                onValueChange = { explanation = it },
                label = { Text("Descrição") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

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

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreateEventModal(
    viewModel: EventViewModel,
    onClose: () -> Unit,
    userId: Int
) {
    var title by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf(60) }
    var eventType by remember { mutableStateOf(EventType.IN_PERSON) }
    var bubble by remember { mutableStateOf<BubbleResponseDTO?>(null) }
    var publicPlace by remember { mutableStateOf(true) }
    var peopleCapacity by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var cep by remember { mutableStateOf("") }
    var platform by remember { mutableStateOf("") }
    var link by remember { mutableStateOf("") }

    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy")
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    var selectedDate by remember { mutableStateOf(LocalDate.now().format(dateFormatter)) }
    var selectedTime by remember { mutableStateOf(LocalTime.now().format(timeFormatter)) }

    val bubbleViewModel = BubbleViewModel()

    val bubbles = bubbleViewModel.bubbleList.observeAsState().value

    Modal(title = "Criar Evento", isVisible = true, onClose = onClose) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Nome do Evento") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                OutlinedTextField(
                    value = selectedDate,
                    onValueChange = { selectedDate = it },
                    label = { Text("Data") },
                    modifier = Modifier.weight(1.3f)
                )

                OutlinedTextField(
                    value = selectedTime,
                    onValueChange = { selectedTime = it },
                    label = { Text("Hora") },
                    modifier = Modifier.weight(0.7f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            var expandedBubble by remember { mutableStateOf(false) }
            OutlinedTextField(
                value = bubble?.title ?: "",
                onValueChange = { },
                label = { Text("Bolha") },
                trailingIcon = {
                    Icon(
                        Icons.Filled.ArrowDropDown,
                        null,
                        Modifier.clickable { expandedBubble = !expandedBubble })
                },
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )
            DropdownMenu(
                expanded = expandedBubble,
                onDismissRequest = { expandedBubble = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                bubbles?.forEach { bubbleItem: BubbleResponseDTO ->
                    DropdownMenuItem(
                        text = { Text(bubbleItem.title!!) },
                        onClick = {
                            bubble = bubbleItem
                            expandedBubble = false
                        })
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = eventType == EventType.IN_PERSON,
                        onClick = { eventType = EventType.IN_PERSON }
                    )
                    Text("Presencial")
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = eventType == EventType.ONLINE,
                        onClick = { eventType = EventType.ONLINE }
                    )
                    Text("Online")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            when (eventType) {
                EventType.IN_PERSON -> {
                    OutlinedTextField(
                        value = address,
                        onValueChange = { address = it },
                        label = { Text("Endereço") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = cep,
                        onValueChange = { cep = it },
                        label = { Text("CEP") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                EventType.ONLINE -> {
                    OutlinedTextField(
                        value = platform,
                        onValueChange = { platform = it },
                        label = { Text("Plataforma") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = link,
                        onValueChange = { link = it },
                        label = { Text("Link") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }


            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val localDate = LocalDate.parse(selectedDate, dateFormatter)
                    val localTime = LocalTime.parse(selectedTime, timeFormatter)
                    val dateTime = LocalDateTime.of(localDate, localTime).toString()

                    val eventRequest = when (eventType) {
                        EventType.IN_PERSON -> EventInPersonRequestDTO(
                            title,
                            dateTime,
                            duration,
                            userId,
                            bubble!!.idBubble!!,
                            publicPlace,
                            if (peopleCapacity.isNotBlank()) peopleCapacity.toInt() else null,
                            Address(1, "01414001", "SP", "São Paulo", "Bairro", "Rua", "123")
                        )

                        EventType.ONLINE -> EventOnlineRequestDTO(
                            title,
                            dateTime,
                            duration,
                            userId,
                            bubble!!.idBubble!!,
                            link,
                            platform,
                        )
                    }
                    // Chama a função correta do ViewModel
                    when (eventType) {
                        EventType.IN_PERSON -> viewModel.createInPersonEvent(eventRequest as EventInPersonRequestDTO)
                        EventType.ONLINE -> viewModel.createOnlineEvent(eventRequest as EventOnlineRequestDTO)
                    }
                    onClose()
                },
                enabled = title.isNotBlank() && bubble != null &&
                        ((eventType == EventType.IN_PERSON && address.isNotBlank()) ||
                                (eventType == EventType.ONLINE && platform.isNotBlank() && link.isNotBlank())),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Criar Evento")
            }
        }
    }
}

enum class EventType {
    IN_PERSON, ONLINE
}

fun Category.toCapitalizedPortuguese(): String {
    return when (this) {
        Category.SPORTS -> "Esportes"
        Category.MUSIC -> "Música"
        Category.GAME -> "Games"
        Category.ART -> "Arte"
        Category.TECHNOLOGY -> "Tecnologia"
        Category.SCIENCE -> "Ciência"
        Category.CULINARY -> "Gastronomia"
        Category.READING -> "Leitura"
    }
}

@Preview
@Composable

fun PreviewModal() {
    CreateEventModal(viewModel = EventViewModel(), onClose = { /*TODO*/ }, userId = 1)
}
