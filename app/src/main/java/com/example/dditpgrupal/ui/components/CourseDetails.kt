package com.example.dditpgrupal.ui.components

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Domain
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Grade
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dditpgrupal.data.Course
import com.example.dditpgrupal.data.dummyCourseList
import com.example.dditpgrupal.data.enums.CourseMode
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import kotlin.math.roundToInt

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

                AbsenceStatisticsPanel(
                    absences = course.absences ?: 0,
                    absenceDates = course.absenceDates,
                )
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        ) {
            val grade = course.grade
            val gradeFraction = (grade / 10.0).coerceIn(0.0, 1.0).toFloat()
            val (gradeStatus, gradeColor) =
                when {
                    grade >= 8 -> "Promocionado" to MaterialTheme.colorScheme.primary
                    grade >= 6 -> "Aprobado" to MaterialTheme.colorScheme.primary
                    grade >= 4 -> "Cursada aprobada" to MaterialTheme.colorScheme.tertiary
                    else -> "Desaprobado" to MaterialTheme.colorScheme.error
                }

            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Grade,
                        contentDescription = null,
                        tint = course.color,
                        modifier = Modifier.size(24.dp),
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "Promedio actual",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(end = 24.dp),
                    ) {
                        Text(
                            text = "%.1f".format(grade),
                            style = MaterialTheme.typography.displaySmall,
                            fontWeight = FontWeight.Bold,
                            color = gradeColor,
                        )
                        Text(
                            text = "/ 10",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier =
                                    Modifier
                                        .size(10.dp)
                                        .background(gradeColor, RoundedCornerShape(2.dp)),
                            )
                            Spacer(Modifier.width(6.dp))
                            Text(
                                text = gradeStatus,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.SemiBold,
                                color = gradeColor,
                            )
                        }
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = "Estado de cursada",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }

                Box(
                    modifier = Modifier.fillMaxWidth().height(8.dp),
                ) {
                    LinearProgressIndicator(
                        progress = { gradeFraction },
                        modifier = Modifier.fillMaxWidth().height(8.dp),
                        color = gradeColor,
                        trackColor = gradeColor.copy(alpha = 0.15f),
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "0",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Text(
                        text = "4",
                        style = MaterialTheme.typography.labelSmall,
                        color = if (grade >= 4) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Text(
                        text = "6",
                        style = MaterialTheme.typography.labelSmall,
                        color = if (grade >= 6) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Text(
                        text = "10",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        ) {
            val elapsedDays = ChronoUnit.DAYS.between(course.semesterStart, LocalDate.now())
            val totalWeeks = ChronoUnit.WEEKS.between(course.semesterStart, course.semesterEnd)
            val elapsedWeeks = ChronoUnit.WEEKS.between(course.semesterStart, LocalDate.now())
            val currentWeek = (elapsedWeeks + 1).toInt().coerceIn(1, totalWeeks.toInt())
            val progressPercent = (semesterProgress * 100).roundToInt()

            val nextMilestone =
                course.importantDates
                    .filter { !it.isBefore(LocalDate.now()) }
                    .minOrNull()

            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text(
                    text = "Progreso de cuatrimestre",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Column {
                        Text(
                            text = "$progressPercent%",
                            style = MaterialTheme.typography.displaySmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                        )
                        Text(
                            text = "completado",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "Semana $currentWeek",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                        Text(
                            text = "de $totalWeeks",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }

                val animatedProgress by animateFloatAsState(
                    targetValue = semesterProgress,
                    animationSpec = tween(durationMillis = 1200),
                    label = "semesterProgress",
                )

                var barWidth by remember { mutableStateOf(0.dp) }
                val density = LocalDensity.current

                Box(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(12.dp)
                            .onSizeChanged {
                                barWidth = with(density) { it.width.toDp() }
                            },
                ) {
                    LinearProgressIndicator(
                        progress = { animatedProgress },
                        modifier = Modifier.fillMaxWidth().height(12.dp),
                        color = course.color,
                        trackColor = course.color.copy(alpha = 0.15f),
                    )

                    course.importantDates.forEach { date ->
                        val fraction =
                            ChronoUnit.DAYS.between(course.semesterStart, date).toFloat() /
                                totalDays.toFloat()
                        val isPast = date.isBefore(LocalDate.now())
                        val markerColor =
                            if (isPast) {
                                course.color
                            } else {
                                course.color.copy(alpha = 0.4f)
                            }

                        Box(
                            modifier =
                                Modifier
                                    .align(Alignment.Center)
                                    .offset(x = barWidth * fraction - 4.dp)
                                    .size(8.dp, 16.dp)
                                    .background(markerColor, RoundedCornerShape(2.dp)),
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = course.semesterStart.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Text(
                        text = course.semesterEnd.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }

                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))

                if (nextMilestone != null) {
                    val daysUntil = ChronoUnit.DAYS.between(LocalDate.now(), nextMilestone)
                    val weeksUntil = ChronoUnit.WEEKS.between(LocalDate.now(), nextMilestone)

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier =
                                Modifier
                                    .size(36.dp)
                                    .background(
                                        course.color.copy(alpha = 0.15f),
                                        RoundedCornerShape(10.dp),
                                    ),
                            contentAlignment = Alignment.Center,
                        ) {
                            Icon(
                                imageVector = Icons.Default.Flag,
                                contentDescription = null,
                                tint = course.color,
                                modifier = Modifier.size(20.dp),
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Próxima fecha",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                            Text(
                                text = nextMilestone.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onSurface,
                            )
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = "$daysUntil días",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                            )
                            Text(
                                text = "($weeksUntil semanas)",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                    }
                } else {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp),
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "No hay más hitos planificados",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }
        }

        val streak = calculateStreak(course.studyHabits)
        val weeks = buildWeeks(course.semesterStart, course.semesterEnd, course.studyHabits)
        val totalMinutes = course.studyHabits.values.sum()
        val totalHours = totalMinutes / 60
        val totalRemainderMinutes = totalMinutes % 60
        val activeDays = course.studyHabits.values.count { it > 0 }
        val avgMinutes = if (activeDays > 0) totalMinutes / activeDays else 0

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        ) {
            val dayLabels = listOf("L", "M", "M", "J", "V", "S", "D")

            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.AutoGraph,
                        contentDescription = null,
                        tint = course.color,
                        modifier = Modifier.size(24.dp),
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "Hábitos de estudio",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    StatItem(label = "Total", value = "${totalHours}h ${totalRemainderMinutes}m")
                    StatItem(label = "Promedio", value = "${avgMinutes}min/día")
                    StatItem(
                        label = "Racha",
                        value = "$streak día${if (streak != 1) "s" else ""}",
                        highlight = streak > 0,
                    )
                }

                Row {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(2.dp),
                        modifier = Modifier.padding(end = 4.dp),
                    ) {
                        Spacer(Modifier.height(20.dp))
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
                        Spacer(Modifier.height(16.dp))
                    }

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        itemsIndexed(weeks) { weekIndex, week ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(2.dp),
                            ) {
                                val firstDate = week.first().first
                                val prevWeek = weeks.getOrNull(weekIndex - 1)
                                val showMonthLabel =
                                    weekIndex == 0 ||
                                        firstDate.monthValue != (
                                            prevWeek?.first()?.first?.monthValue
                                                ?: -1
                                        )

                                Box(
                                    modifier = Modifier.height(20.dp),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    if (showMonthLabel) {
                                        val monthName =
                                            when (firstDate.monthValue) {
                                                1 -> "ENE"
                                                2 -> "FEB"
                                                3 -> "MAR"
                                                4 -> "ABR"
                                                5 -> "MAY"
                                                6 -> "JUN"
                                                7 -> "JUL"
                                                8 -> "AGO"
                                                9 -> "SEP"
                                                10 -> "OCT"
                                                11 -> "NOV"
                                                else -> "DIC"
                                            }
                                        Text(
                                            text = monthName,
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.primary,
                                        )
                                    }
                                }

                                week.forEach { (date, minutes) ->
                                    val isToday = date == LocalDate.now()
                                    Box(
                                        modifier =
                                            Modifier
                                                .size(16.dp)
                                                .background(
                                                    color = heatmapColor(minutes, course.color),
                                                    shape = RoundedCornerShape(3.dp),
                                                ).then(
                                                    if (isToday) {
                                                        Modifier.border(
                                                            1.5.dp,
                                                            MaterialTheme.colorScheme.onSurface,
                                                            RoundedCornerShape(3.dp),
                                                        )
                                                    } else {
                                                        Modifier
                                                    },
                                                ),
                                    )
                                }

                                Text(
                                    text = "${weekIndex + 1}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
                                )
                            }
                        }
                    }
                }

                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    listOf(
                        0 to "0 min",
                        30 to "< 45 min",
                        60 to "< 90 min",
                        120 to "90+ min",
                    ).forEach { (minutes, label) ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier =
                                    Modifier
                                        .size(12.dp)
                                        .background(
                                            heatmapColor(minutes, course.color),
                                            RoundedCornerShape(2.dp),
                                        ),
                            )
                            Spacer(Modifier.width(4.dp))
                            Text(
                                text = label,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
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
                    style = MaterialTheme.typography.titleLarge,
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
                    style = MaterialTheme.typography.titleLarge,
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

@Suppress("ktlint:standard:function-naming")
@Composable
private fun StatItem(
    label: String,
    value: String,
    highlight: Boolean = false,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = if (highlight) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
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
    baseColor: Color,
): Color =
    when {
        minutes == 0 -> baseColor.copy(alpha = 0.05f)
        minutes <= 45 -> baseColor.copy(alpha = 0.25f)
        minutes <= 90 -> baseColor.copy(alpha = 0.55f)
        else -> baseColor.copy(alpha = 1.0f)
    }

@RequiresApi(Build.VERSION_CODES.O)
@Suppress("ktlint:standard:function-naming")
@Preview
@Composable
private fun CourseDetailsPreview() {
    CourseDetails(course = dummyCourseList.first())
}
