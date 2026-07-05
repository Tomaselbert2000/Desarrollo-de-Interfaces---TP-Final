package com.example.dditpgrupal.ui.components

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Domain
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dditpgrupal.data.Course
import com.example.dditpgrupal.data.dummyCourseList
import com.example.dditpgrupal.data.enums.CourseMode
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@OptIn(ExperimentalLayoutApi::class)
@SuppressLint("NewApi")
@Suppress("ktlint:standard:function-naming")
@Composable
fun CourseDetails(course: Course) {
    val totalDays = ChronoUnit.DAYS.between(course.semesterStart, course.semesterEnd)
    val elapsedDays = ChronoUnit.DAYS.between(course.semesterStart, LocalDate.now())
    val semesterProgress = (elapsedDays.toFloat() / totalDays.toFloat()).coerceIn(0f, 1f)

    Column(
        modifier =
            Modifier
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text(
                    text = "Inasistencias",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                AbsenceStatisticsPanel(absences = course.absences ?: 0, absenceDates = course.absenceDates)
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text(
                    text = "Fechas importantes",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                LinearProgressIndicator(
                    progress = { semesterProgress },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )

                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    course.importantDates.forEach { date ->
                        val isPast = date.isBefore(LocalDate.now())
                        AssistChip(
                            onClick = {},
                            label = {
                                Text(
                                    text = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                                    style = MaterialTheme.typography.labelSmall,
                                )
                            },
                            colors =
                                if (isPast) {
                                    AssistChipDefaults.assistChipColors(
                                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                        labelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                    )
                                } else {
                                    AssistChipDefaults.assistChipColors(
                                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                                        labelColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                    )
                                },
                        )
                    }
                }
            }
        }

        val streak = calculateStreak(course.studyHabits)
        val weeks = buildWeeks(course.semesterStart, course.semesterEnd, course.studyHabits)

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text(
                    text = "Tu racha de d\u00edas: $streak",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                val dayLabels = listOf("L", "M", "M", "J", "V", "S", "D")

                Row {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(2.dp),
                        modifier = Modifier.padding(end = 4.dp),
                    ) {
                        dayLabels.forEach { label ->
                            Box(
                                modifier = Modifier.size(16.dp),
                                contentAlignment = Alignment.Center,
                            ) {
                                Text(
                                    text = label,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                )
                            }
                        }
                    }

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        items(weeks) { week ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(2.dp),
                            ) {
                                week.forEach { (_, minutes) ->
                                    Box(
                                        modifier =
                                            Modifier
                                                .size(16.dp)
                                                .background(
                                                    color = heatmapColor(minutes, course.color),
                                                    shape = RoundedCornerShape(3.dp),
                                                ),
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text(
                    text = "Detalles académicos",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                DetailRow(
                    icon = Icons.Default.Domain,
                    label = "Departamento",
                    text = course.departament,
                )

                DetailRow(
                    icon = Icons.Default.Tag,
                    label = "Comisi\u00f3n",
                    text = course.commission,
                )

                DetailRow(
                    icon = if (course.mode == CourseMode.PRESENCIAL) Icons.Default.School else Icons.Default.Computer,
                    label = "Modalidad",
                    text = course.mode.name,
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
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text(
                    text = "Equipo docente",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                DetailRow(
                    icon = Icons.Default.Group,
                    label = "Docente/s",
                    text = course.teacher.joinToString(", "),
                )
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
private fun DetailRow(
    icon: ImageVector,
    label: String,
    text: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Top,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(32.dp),
        )
        Column(
            modifier = Modifier.padding(start = 8.dp),
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Suppress("ktlint:standard:function-naming")
@Composable
private fun AbsenceStatisticsPanel(
    absences: Int,
    absenceDates: List<LocalDate> = emptyList(),
) {
    val (status, color) =
        when {
            absences <= 2 -> "Regular" to MaterialTheme.colorScheme.primary
            absences == 3 -> "Atención" to MaterialTheme.colorScheme.tertiary
            else -> "Libre" to MaterialTheme.colorScheme.error
        }
    val progress = (absences / 4.0f).coerceAtMost(1.0f)

    Column {
        Box(
            modifier = Modifier.fillMaxWidth().padding(32.dp),
            contentAlignment = Alignment.Center,
        ) {
            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.size(120.dp),
                    strokeWidth = 12.dp,
                    color = color,
                    trackColor = color.copy(alpha = 0.15f),
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "$absences/4",
                        style = MaterialTheme.typography.titleLarge,
                        color = color,
                    )
                    Text(
                        text = status,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = color,
                    )
                }
            }
        }

        if (absenceDates.isNotEmpty()) {
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 16.dp),
                color = MaterialTheme.colorScheme.outlineVariant,
            )

            FlowRow(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                absenceDates.forEach { date ->
                    AssistChip(
                        onClick = {},
                        label = {
                            Text(
                                text = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                                style = MaterialTheme.typography.labelSmall,
                            )
                        },
                        colors =
                            AssistChipDefaults.assistChipColors(
                                containerColor = MaterialTheme.colorScheme.errorContainer,
                                labelColor = MaterialTheme.colorScheme.onErrorContainer,
                            ),
                    )
                }
            }
        }
    }
}

@SuppressLint("NewApi")
private fun calculateStreak(studyHabits: Map<LocalDate, Int>): Int {
    var streak = 0
    var date = LocalDate.now()
    while (true) {
        val minutes = studyHabits[date] ?: 0
        if (minutes > 0) {
            streak++
            date = date.minusDays(1)
        } else {
            break
        }
    }
    return streak
}

@SuppressLint("NewApi")
private fun buildWeeks(
    start: LocalDate,
    end: LocalDate,
    studyHabits: Map<LocalDate, Int>,
): List<List<Pair<LocalDate, Int>>> {
    val weeks = mutableListOf<List<Pair<LocalDate, Int>>>()
    val monday = start.with(java.time.DayOfWeek.MONDAY)
    val sunday = end.with(java.time.DayOfWeek.SUNDAY)

    var weekStart = monday
    while (!weekStart.isAfter(sunday)) {
        val days =
            (0..6).map { offset ->
                val date = weekStart.plusDays(offset.toLong())
                val minutes = studyHabits[date] ?: 0
                date to minutes
            }
        weeks.add(days)
        weekStart = weekStart.plusWeeks(1)
    }
    return weeks
}

private fun heatmapColor(
    minutes: Int,
    baseColor: androidx.compose.ui.graphics.Color,
): androidx.compose.ui.graphics.Color =
    when {
        minutes == 0 -> baseColor.copy(alpha = 0.08f)
        minutes <= 45 -> baseColor.copy(alpha = 0.3f)
        minutes <= 90 -> baseColor.copy(alpha = 0.6f)
        else -> baseColor.copy(alpha = 1.0f)
    }

@RequiresApi(Build.VERSION_CODES.O)
@Suppress("ktlint:standard:function-naming")
@Preview
@Composable
private fun CourseDetailsPreview() {
    CourseDetails(course = dummyCourseList.first())
}
