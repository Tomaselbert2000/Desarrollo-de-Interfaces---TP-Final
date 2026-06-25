package com.example.dditpgrupal.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
val dummySubjectList =
    mutableListOf(
        Course(
            "Desarrollo de Interfaces",
            "01-3900",
            "Turno noche, 19:00 a 23:00",
            CourseMode.PRESENCIAL,
            listOf("Heliana Vera", "Gonzalo Rivas"),
            listOf(
                LocalDate.of(2026, 5, 20),
                LocalDate.of(2026, 6, 30),
            ),
            absences = 0,
            studentsAtRisk = null,
            color = Color.Green,
        ),
        Course(
            "Matemática General",
            "01-6300",
            "Turno mañana, 8:00 a 12:00",
            CourseMode.VIRTUAL,
            listOf("Liliana Romano", "Federico Pileci"),
            listOf(
                LocalDate.of(2026, 5, 26),
                LocalDate.of(2026, 6, 18),
            ),
            absences = 1,
            studentsAtRisk = null,
            color = Color.Blue,
        ),
        Course(
            "Matemática General",
            "01-2900",
            "Turno noche, 19:00 a 23:00",
            CourseMode.SEMIPRESENCIAL,
            listOf("Liliana Romano", "Federico Pileci"),
            listOf(
                LocalDate.of(2026, 5, 26),
                LocalDate.of(2026, 6, 18),
            ),
            absences = 1,
            studentsAtRisk = null,
            color = Color.Cyan,
        ),
    )

data class Course(
    val name: String,
    val commission: String,
    val shiftAndSchedule: String,
    val mode: CourseMode,
    val teacher: List<String>,
    val importantDates: List<LocalDate>,
    val absences: Int?,
    val studentsAtRisk: Int?,
    val color: Color,
)
