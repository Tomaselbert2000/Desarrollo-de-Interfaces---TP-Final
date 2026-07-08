package com.example.dditpgrupal.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dditpgrupal.data.Message
import com.example.dditpgrupal.data.dummyMessages
import com.example.dditpgrupal.ui.components.TextFormatToolbar

data class ChatMessage(
    val senderName: String,
    val text: String,
    val timestamp: String,
    val isMine: Boolean,
)

@RequiresApi(Build.VERSION_CODES.O)
@Suppress("ktlint:standard:function-naming")
@Composable
fun MessageResponseScreen(
    message: Message,
    onBackClick: () -> Unit = {},
) {
    var replyText by remember { mutableStateOf("") }

    val conversation =
        remember(message) {
            listOf(
                ChatMessage(
                    senderName = message.senderName,
                    text = message.preview,
                    timestamp = "${message.date.dayOfMonth}/${message.date.monthValue}/${message.date.year}",
                    isMine = false,
                ),
                ChatMessage(
                    senderName = "Tomas",
                    text = @Suppress("ktlint:standard:max-line-length")
                    "Hola, gracias por el mensaje. Quer\u00eda consultarle acerca de los detalles de entrega. \u00bfHay alguna fecha l\u00edmite flexible?",
                    timestamp = "${message.date.dayOfMonth + 1}/${message.date.monthValue}/${message.date.year}",
                    isMine = true,
                ),
                ChatMessage(
                    senderName = message.senderName,
                    text = @Suppress("ktlint:standard:max-line-length")
                    "Buen d\u00eda Tomas. La fecha l\u00edmite es la indicada en el campus, pero pueden solicitar una pr\u00f3rroga enviando un mail justificado con al menos 48 hs de antelaci\u00f3n.",
                    timestamp = "${message.date.dayOfMonth + 2}/${message.date.monthValue}/${message.date.year}",
                    isMine = false,
                ),
                ChatMessage(
                    senderName = "Tomas",
                    text = "Perfecto, muchas gracias por la pronta respuesta. Voy a coordinar con mi grupo para enviarlo antes del plazo.",
                    timestamp = "${message.date.dayOfMonth + 2}/${message.date.monthValue}/${message.date.year}",
                    isMine = true,
                ),
                ChatMessage(
                    senderName = message.senderName,
                    text = "De nada, ante cualquier otra consulta no duden en escribirme. Saludos.",
                    timestamp = "${message.date.dayOfMonth + 3}/${message.date.monthValue}/${message.date.year}",
                    isMine = false,
                ),
            )
        }

    Column(modifier = Modifier.fillMaxSize().imePadding()) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver",
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = message.senderName,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = message.subject,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }

        HorizontalDivider()

        LazyColumn(
            modifier =
                Modifier
                    .weight(1f)
                    .fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(conversation, key = { "${it.timestamp}_${it.text}" }) { chat ->
                ChatBubble(chat = chat)
            }
        }

        HorizontalDivider()

        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            AnimatedVisibility(
                visible = true,
                enter = fadeIn() + expandVertically(expandFrom = Alignment.Top),
                exit = fadeOut(),
            ) {
                TextFormatToolbar(
                    onToolClick = { index ->
                        replyText = applyFormatting(replyText, index)
                    },
                )
            }

            OutlinedTextField(
                value = replyText,
                onValueChange = { replyText = it },
                placeholder = { Text("Escrib\u00ed tu respuesta...") },
                modifier =
                    Modifier
                        .fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                textStyle = MaterialTheme.typography.bodyMedium,
                colors =
                    OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    ),
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = { },
                    modifier =
                        Modifier
                            .size(40.dp)
                            .background(
                                MaterialTheme.colorScheme.surface,
                                RoundedCornerShape(10.dp),
                            ),
                ) {
                    Icon(
                        imageVector = Icons.Default.AttachFile,
                        contentDescription = "Adjuntar archivo",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(20.dp),
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                IconButton(
                    onClick = { },
                    modifier =
                        Modifier
                            .size(40.dp)
                            .background(
                                MaterialTheme.colorScheme.surface,
                                RoundedCornerShape(10.dp),
                            ),
                ) {
                    Icon(
                        imageVector = Icons.Default.Image,
                        contentDescription = "Adjuntar imagen",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(20.dp),
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))

                Box(
                    modifier =
                        Modifier
                            .height(40.dp)
                            .background(
                                MaterialTheme.colorScheme.primary,
                                RoundedCornerShape(12.dp),
                            ).clickable { }
                            .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(18.dp),
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Responder",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onPrimary,
                        )
                    }
                }
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
private fun ChatBubble(chat: ChatMessage) {
    val bubbleColor =
        if (chat.isMine) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            MaterialTheme.colorScheme.surfaceVariant
        }
    val textColor =
        if (chat.isMine) {
            MaterialTheme.colorScheme.onPrimaryContainer
        } else {
            MaterialTheme.colorScheme.onSurfaceVariant
        }
    val alignment = if (chat.isMine) Alignment.End else Alignment.Start

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = alignment,
    ) {
        Card(
            shape =
                RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp,
                    bottomStart = if (chat.isMine) 16.dp else 4.dp,
                    bottomEnd = if (chat.isMine) 4.dp else 16.dp,
                ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            colors = CardDefaults.cardColors(containerColor = bubbleColor),
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = chat.text,
                    style = MaterialTheme.typography.bodyMedium,
                    color = textColor,
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = chat.timestamp,
                    style = MaterialTheme.typography.labelSmall,
                    color = textColor.copy(alpha = 0.6f),
                    modifier = Modifier.align(Alignment.End),
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Suppress("ktlint:standard:function-naming")
@Preview
@Composable
private fun MessageResponseScreenPreview() {
    MessageResponseScreen(
        message = dummyMessages.first(),
        onBackClick = {},
    )
}

private fun applyFormatting(text: String, toolIndex: Int): String {
    val prefix =
        when (toolIndex) {
            0 -> "**negrita**"
            1 -> "*cursiva*"
            2 -> "<u>subrayado</u>"
            3 -> "\n- Elemento de lista"
            4 -> "\n> Cita"
            5 -> "[Bot\u00f3n]"
            else -> ""
        }
    return if (text.isBlank()) prefix else "$text $prefix"
}
