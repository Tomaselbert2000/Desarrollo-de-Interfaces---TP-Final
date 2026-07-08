package com.example.dditpgrupal.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
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
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.dditpgrupal.data.Note
import com.example.dditpgrupal.data.dummyNotesList
import kotlin.math.roundToInt

@RequiresApi(Build.VERSION_CODES.O)
@Suppress("ktlint:standard:function-naming")
@Composable
fun NotepadScreen(
    notes: List<Note>,
    onAddNote: () -> Unit = {},
    onEditNote: (Note) -> Unit = {},
    onDeleteNote: (Note) -> Unit = {},
    onReorder: (Int, Int) -> Unit = { _, _ -> },
) {
    var draggedItemIndex by remember { mutableStateOf<Int?>(null) }
    var dragOffset by remember { mutableStateOf(Offset.Zero) }
    var itemHeights by remember { mutableStateOf(mutableMapOf<Int, Int>()) }
    var itemWidth by remember { mutableStateOf(0) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddNote) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar nota",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            }
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
        ) {
            if (notes.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Icon(
                            imageVector = Icons.Default.EditNote,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(64.dp),
                        )
                        Text(
                            text = "Aún no hay notas",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(top = 16.dp),
                        )
                    }
                }
            } else {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalItemSpacing = 8.dp,
                ) {
                    itemsIndexed(
                        notes,
                        key = { _, note -> note.name + note.creationDate },
                    ) { index, note ->
                        val isDragging = draggedItemIndex == index
                        Box(
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .animateItem()
                                    .zIndex(if (isDragging) 1f else 0f)
                                    .graphicsLayer {
                                        translationX = if (isDragging) dragOffset.x else 0f
                                        translationY = if (isDragging) dragOffset.y else 0f
                                        scaleX = if (isDragging) 1.05f else 1f
                                        scaleY = if (isDragging) 1.05f else 1f
                                        rotationZ =
                                            if (isDragging) {
                                                (dragOffset.x * 0.03f).coerceIn(
                                                    -3f,
                                                    3f,
                                                )
                                            } else {
                                                0f
                                            }
                                        shadowElevation = if (isDragging) 12f else 0f
                                    }.onSizeChanged { size ->
                                        itemHeights =
                                            itemHeights
                                                .toMutableMap()
                                                .apply { put(index, size.height) }
                                        if (itemWidth == 0) itemWidth = size.width
                                    }.pointerInput(Unit) {
                                        detectDragGesturesAfterLongPress(
                                            onDragStart = {
                                                draggedItemIndex = index
                                                dragOffset = Offset.Zero
                                            },
                                            onDrag = { change, dragAmount ->
                                                change.consume()
                                                dragOffset += dragAmount
                                            },
                                            onDragEnd = {
                                                val draggedIdx =
                                                    draggedItemIndex
                                                        ?: return@detectDragGesturesAfterLongPress
                                                val height =
                                                    itemHeights[draggedIdx]
                                                        ?: return@detectDragGesturesAfterLongPress
                                                val col = draggedIdx % 2
                                                val row = draggedIdx / 2
                                                val halfWidth = (itemWidth / 2f).coerceAtLeast(1f)
                                                val deltaCol =
                                                    when {
                                                        dragOffset.x > halfWidth -> 1
                                                        dragOffset.x < -halfWidth -> -1
                                                        else -> 0
                                                    }
                                                val deltaRow = (dragOffset.y / height).roundToInt()
                                                val newCol = (col + deltaCol).coerceIn(0, 1)
                                                val newRow = (row + deltaRow).coerceAtLeast(0)
                                                val targetIndex =
                                                    (newRow * 2 + newCol).coerceAtMost(notes.lastIndex)
                                                if (targetIndex != draggedIdx) {
                                                    onReorder(draggedIdx, targetIndex)
                                                }
                                                draggedItemIndex = null
                                                dragOffset = Offset.Zero
                                            },
                                            onDragCancel = {
                                                draggedItemIndex = null
                                                dragOffset = Offset.Zero
                                            },
                                        )
                                    },
                        ) {
                            NoteCard(
                                note = note,
                                onEditNote = { onEditNote(note) },
                                onDeleteNote = { onDeleteNote(note) },
                            )
                        }
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Suppress("ktlint:standard:function-naming")
@Composable
private fun NoteCard(
    note: Note,
    onEditNote: () -> Unit = {},
    onDeleteNote: () -> Unit = {},
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Text(
                text = note.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = note.storedText,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
            )

            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "${note.creationDate.dayOfMonth}/${note.creationDate.monthValue}/${note.creationDate.year}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                    modifier = Modifier.weight(1f),
                )

                IconButton(onClick = onEditNote) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar nota",
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }

                IconButton(onClick = onDeleteNote) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Eliminar nota",
                        tint = MaterialTheme.colorScheme.error,
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Suppress("ktlint:standard:function-naming")
@Preview
@Composable
private fun NotepadScreenPreview() {
    NotepadScreen(notes = dummyNotesList)
}
