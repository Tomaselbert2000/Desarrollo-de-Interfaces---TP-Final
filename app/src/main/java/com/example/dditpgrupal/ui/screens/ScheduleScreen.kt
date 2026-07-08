package com.example.dditpgrupal.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dditpgrupal.data.Course
import com.example.dditpgrupal.data.dummyCourseList
import com.example.dditpgrupal.ui.components.CalendarAlertCard
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@RequiresApi(Build.VERSION_CODES.O)
@Suppress("ktlint:standard:function-naming")
@Composable
fun ScheduleScreen(course: Course) {
    var selectedIndex by remember { mutableStateOf<Int?>(null) }
    var currentMonth by remember { mutableStateOf(LocalDate.now().withDayOfMonth(1)) }

    val sampleActivities =
        listOf(
            "Parcial 1" to Icons.AutoMirrored.Filled.Assignment,
            "Entrega TP" to Icons.Default.Description,
            "Recuperatorio" to Icons.Default.Edit,
            "Trabajo Final" to Icons.Default.CheckCircle,
        )

    val sampleTopics =
        listOf(
            listOf(
                "Unidad 1: Introducci\u00f3n",
                "Unidad 2: Conceptos avanzados",
                "Unidad 3: Pr\u00e1ctica integradora",
            ),
            listOf(
                "Consigna",
                "Formato de entrega",
                "Fecha l\u00edmite",
                "R\u00fabrica de evaluaci\u00f3n",
            ),
            listOf("Temas a recuperar"),
            listOf(
                "Definici\u00f3n del proyecto",
                "Planificaci\u00f3n",
                "Implementaci\u00f3n",
                "Pruebas",
                "Documentaci\u00f3n",
                "Presentaci\u00f3n final",
            ),
        )

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        CalendarAlertCard()

        CalendarCard(
            currentMonth = currentMonth,
            importantDates = course.importantDates,
            courseColor = course.color,
            selectedIndex = selectedIndex,
            onMonthChange = {
                currentMonth = it
                selectedIndex = null
            },
            onDateClick = { selectedIndex = it },
        )

        if (selectedIndex != null) {
            val index = selectedIndex!!
            val date = course.importantDates[index]
            val (activityName, icon) = sampleActivities[index % sampleActivities.size]
            val topics = sampleTopics[index % sampleTopics.size]

            DateDetailCard(
                activityName = activityName,
                icon = icon,
                date = date,
                topics = topics,
                courseColor = course.color,
            )
        } else {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 48.dp),
                contentAlignment = Alignment.Center,
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
                        modifier = Modifier.size(64.dp),
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Seleccion\u00e1 una fecha importante para ver sus detalles",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Suppress("ktlint:standard:function-naming")
@Composable
private fun CalendarCard(
    currentMonth: LocalDate,
    importantDates: List<LocalDate>,
    courseColor: Color,
    selectedIndex: Int?,
    onMonthChange: (LocalDate) -> Unit,
    onDateClick: (Int) -> Unit,
) {
    val daysInMonth = currentMonth.lengthOfMonth()
    val firstDayOfMonth = currentMonth.dayOfWeek
    val startOffset = (firstDayOfMonth.value + 6) % 7
    val totalCells = startOffset + daysInMonth
    val weeksNeeded = (totalCells + 6) / 7

    val weekDays = listOf("L", "M", "M", "J", "V", "S", "D")

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(onClick = { onMonthChange(currentMonth.minusMonths(1)) }) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Mes anterior",
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = formatMonth(currentMonth),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { onMonthChange(currentMonth.plusMonths(1)) }) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "Mes siguiente",
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                weekDays.forEach { day ->
                    Text(
                        text = day,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                    )
                }
            }

            for (week in 0 until weeksNeeded) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    for (dayOfWeek in 0 until 7) {
                        val day = week * 7 + dayOfWeek - startOffset + 1
                        val isValidDay = day in 1..daysInMonth

                        if (isValidDay) {
                            val date =
                                LocalDate.of(
                                    currentMonth.year,
                                    currentMonth.month,
                                    day,
                                )
                            val dateIndex = importantDates.indexOf(date)
                            val isHighlighted = dateIndex >= 0
                            val isSelected = dateIndex >= 0 && dateIndex == selectedIndex

                            val cellModifier =
                                if (isHighlighted) {
                                    Modifier.clickable { onDateClick(dateIndex) }
                                } else {
                                    Modifier
                                }

                            val borderModifier =
                                if (isHighlighted) {
                                    Modifier.border(
                                        BorderStroke(
                                            1.dp,
                                            MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                                        ),
                                        CircleShape,
                                    )
                                } else {
                                    Modifier
                                }

                            Box(
                                modifier =
                                    Modifier
                                        .weight(1f)
                                        .aspectRatio(1f)
                                        .then(cellModifier)
                                        .then(borderModifier)
                                        .background(
                                            when {
                                                isSelected -> courseColor.copy(alpha = 0.35f)
                                                isHighlighted -> courseColor.copy(alpha = 0.25f)
                                                else -> Color.Transparent
                                            },
                                            CircleShape,
                                        ),
                                contentAlignment = Alignment.Center,
                            ) {
                                Text(
                                    text = day.toString(),
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight =
                                        if (isHighlighted) {
                                            FontWeight.SemiBold
                                        } else {
                                            FontWeight.Normal
                                        },
                                    color =
                                        if (isHighlighted) {
                                            MaterialTheme.colorScheme.onSurface
                                        } else {
                                            MaterialTheme.colorScheme.onSurface
                                        },
                                )
                            }
                        } else {
                            Spacer(modifier = Modifier.weight(1f).aspectRatio(1f))
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
private fun DateDetailCard(
    activityName: String,
    icon: ImageVector,
    date: LocalDate,
    topics: List<String>,
    courseColor: Color,
) {
    val daysUntil = ChronoUnit.DAYS.between(LocalDate.now(), date)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier =
                        Modifier
                            .size(44.dp)
                            .background(courseColor.copy(alpha = 0.15f), RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = courseColor,
                        modifier = Modifier.size(24.dp),
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = activityName,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Text(
                        text = "${date.dayOfMonth}/${date.monthValue}/${date.year}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                if (daysUntil > 0) {
                    Box(
                        modifier =
                            Modifier
                                .background(
                                    MaterialTheme.colorScheme.primaryContainer,
                                    RoundedCornerShape(8.dp),
                                ).padding(horizontal = 8.dp, vertical = 4.dp),
                    ) {
                        Text(
                            text = "Faltan $daysUntil d\u00edas",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                        )
                    }
                }
            }

            if (topics.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Contenido",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 8.dp),
                )

                topics.forEach { topic ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier =
                                Modifier
                                    .size(6.dp)
                                    .background(courseColor, CircleShape),
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = topic,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                    if (topic != topics.last()) {
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun formatMonth(date: LocalDate): String {
    val months =
        listOf(
            "Enero",
            "Febrero",
            "Marzo",
            "Abril",
            "Mayo",
            "Junio",
            "Julio",
            "Agosto",
            "Septiembre",
            "Octubre",
            "Noviembre",
            "Diciembre",
        )
    return "${months[date.monthValue - 1]} ${date.year}"
}

@RequiresApi(Build.VERSION_CODES.O)
@Suppress("ktlint:standard:function-naming")
@Preview
@Composable
private fun ScheduleScreenPreview() {
    ScheduleScreen(course = dummyCourseList.first())
}
