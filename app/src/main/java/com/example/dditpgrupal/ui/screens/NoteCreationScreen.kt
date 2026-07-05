package com.example.dditpgrupal.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import com.example.dditpgrupal.data.Note
import com.example.dditpgrupal.ui.components.TextFormatToolbar

@Suppress("ktlint:standard:function-naming")
@Composable
fun NoteCreationScreen(
    onBackClick: () -> Unit = {},
    onSave: (name: String, text: String, isImportant: Boolean) -> Unit = { _, _, _ -> },
    noteToEdit: Note? = null,
) {
    var noteName by remember { mutableStateOf(noteToEdit?.name ?: "") }
    var noteText by remember { mutableStateOf(noteToEdit?.storedText ?: "") }
    var isImportant by remember { mutableStateOf(noteToEdit?.isImportant ?: false) }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "Volver",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

            Text(
                text = if (noteToEdit != null) "Editar nota" else "Crear nota",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(start = 8.dp),
            )
        }

        Card(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                OutlinedTextField(
                    value = noteName,
                    onValueChange = { noteName = it },
                    placeholder = {
                        Text(
                            text = "T\u00edtulo de la nota",
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = MaterialTheme.typography.bodyLarge,
                    singleLine = true,
                )

                OutlinedTextField(
                    value = noteText,
                    onValueChange = { noteText = it },
                    placeholder = {
                        Text(
                            text = "",
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    },
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .heightIn(min = 200.dp),
                    textStyle = MaterialTheme.typography.bodyLarge,
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Marcar como importante",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                    )

                    Checkbox(
                        checked = isImportant,
                        onCheckedChange = { isImportant = it },
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    IconButton(onClick = { onSave(noteName, noteText, isImportant) }) {
                        Icon(
                            imageVector = Icons.Default.Save,
                            contentDescription = "Guardar nota",
                            tint = MaterialTheme.colorScheme.primary,
                        )
                    }
                }

                TextFormatToolbar()
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview
@Composable
private fun NoteCreationScreenPreview() {
    NoteCreationScreen()
}
