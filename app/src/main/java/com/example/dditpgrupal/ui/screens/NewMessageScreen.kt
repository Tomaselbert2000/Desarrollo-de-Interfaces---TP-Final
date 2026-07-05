package com.example.dditpgrupal.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.automirrored.filled.Subject
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dditpgrupal.ui.components.TextFormatToolbar

private const val MAX_CHARS = 500

private val recipients =
    listOf(
        "Prof. Heliana Vera",
        "Prof. Gonzalo Rivas",
        "Prof. Liliana Romano",
        "Prof. Federico Pileci",
        @Suppress("ktlint:standard:max-line-length")
        "Todos los docentes",
    )

@Suppress("ktlint:standard:function-naming")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewMessageScreen(onBackClick: () -> Unit = {}) {
    var selectedRecipient by remember { mutableStateOf(recipients.first()) }
    var expanded by remember { mutableStateOf(false) }
    var subject by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    val charCount by remember { derivedStateOf { message.length } }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier =
                Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
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
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Nuevo mensaje",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Text(
                        text = "Comunicate con tus docentes",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            ) {
                Column(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(20.dp),
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        ExposedDropdownMenuBox(
                            expanded = expanded,
                            onExpandedChange = { expanded = !expanded },
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            OutlinedTextField(
                                value = selectedRecipient,
                                onValueChange = {},
                                readOnly = true,
                                label = { Text("Destinatario") },
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable).fillMaxWidth(),
                                textStyle = MaterialTheme.typography.bodyMedium,
                                colors =
                                    OutlinedTextFieldDefaults.colors(
                                        unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                                    ),
                            )
                            ExposedDropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                            ) {
                                recipients.forEach { recipient ->
                                    DropdownMenuItem(
                                        text = { Text(recipient) },
                                        onClick = {
                                            selectedRecipient = recipient
                                            expanded = false
                                        },
                                    )
                                }
                            }
                        }
                    }

                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Subject,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(20.dp),
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        OutlinedTextField(
                            value = subject,
                            onValueChange = { if (it.length <= 100) subject = it },
                            placeholder = { Text("Asunto del mensaje") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            textStyle = MaterialTheme.typography.bodyMedium,
                            colors =
                                OutlinedTextFieldDefaults.colors(
                                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                                ),
                        )
                    }

                    OutlinedTextField(
                        value = message,
                        onValueChange = {
                            if (it.length <= MAX_CHARS) {
                                message = it
                            }
                        },
                        placeholder = { Text("Escribí tu mensaje...") },
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .height(220.dp),
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
                        Text(
                            text = "$charCount/$MAX_CHARS",
                            style = MaterialTheme.typography.labelSmall,
                            color =
                                if (charCount >= MAX_CHARS) {
                                    MaterialTheme.colorScheme.error
                                } else {
                                    MaterialTheme.colorScheme.onSurfaceVariant
                                },
                            modifier = Modifier.padding(start = 8.dp),
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        IconButton(
                            onClick = { },
                            modifier =
                                Modifier
                                    .size(40.dp)
                                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(10.dp)),
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
                                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(10.dp)),
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
                                    .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(12.dp))
                                    .clickable { }
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
                                    text = "Enviar",
                                    style = MaterialTheme.typography.titleSmall,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                )
                            }
                        }
                    }
                }
            }

            TextFormatToolbar()
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview
@Composable
private fun NewMessageScreenPreview() {
    NewMessageScreen()
}
