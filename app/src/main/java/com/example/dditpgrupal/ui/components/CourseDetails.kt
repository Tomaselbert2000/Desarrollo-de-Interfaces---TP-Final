package com.example.dditpgrupal.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dditpgrupal.data.Course
import com.example.dditpgrupal.data.dummyCourseList

private fun statusInfo(absences: Int): Pair<String, Color> =
    when {
        absences <= 2 -> "Regular" to Color(0xFF4CAF50)
        absences == 3 -> "Atención" to Color(0xFFFFC107)
        else -> "Libre" to Color(0xFFF44336)
    }

@Suppress("ktlint:standard:function-naming")
@Composable
fun CourseDetails(
    course: Course,
    onBackClick: () -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "Volver",
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
            Text(
                text = course.name,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }

        Card(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                InfoRow(label = "Comisión", value = course.commission)
                InfoRow(label = "Modalidad de cursada", value = course.mode.name)
                InfoRow(label = "Departamento", value = course.departament)
                InfoRow(label = "Docente/s", value = course.teacher.joinToString(", "))

                HorizontalDivider(
                    color = MaterialTheme.colorScheme.outlineVariant,
                )

                Text(
                    text = "Estado actual de cursada",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold,
                )

                if (course.absences != null) {
                    InfoRow(
                        label = "Faltas registradas",
                        value = course.absences.toString(),
                    )

                    val (text, color) = statusInfo(course.absences)
                    Text(
                        text = text,
                        style = MaterialTheme.typography.titleLarge,
                        color = color,
                        fontWeight = FontWeight.Bold,
                    )
                } else {
                    Text(
                        text = "Sin registro de inasistencias",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
private fun InfoRow(
    label: String,
    value: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Suppress("ktlint:standard:function-naming")
@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun CourseDetailPreview() {
    CourseDetails(
        dummyCourseList.first(),
        onBackClick = {},
    )
}
