package com.example.dditpgrupal.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import com.example.dditpgrupal.data.enums.CourseMode
import com.example.dditpgrupal.ui.theme.CourseBlue
import com.example.dditpgrupal.ui.theme.CourseIndigo
import com.example.dditpgrupal.ui.theme.CourseOrange
import com.example.dditpgrupal.ui.theme.CoursePurple
import com.example.dditpgrupal.ui.theme.CourseRed
import com.example.dditpgrupal.ui.theme.CourseTeal
import com.example.dditpgrupal.ui.theme.CourseTealDark
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import kotlin.random.Random

@RequiresApi(Build.VERSION_CODES.O)
val dummyCourseList =
    mutableListOf(
        Course(
            "Desarrollo de Interfaces",
            "01-3900",
            "Turno noche, 19:00 a 23:00",
            "Ingenier\u00eda e Investigaciones Tecnol\u00f3gicas",
            CourseMode.PRESENCIAL,
            listOf("Heliana Vera", "Gonzalo Rivas"),
            listOf(
                LocalDate.of(2026, 4, 22),
                LocalDate.of(2026, 5, 20),
                LocalDate.of(2026, 6, 30),
                LocalDate.of(2026, 7, 1),
                LocalDate.of(2026, 7, 13),
            ),
            listOf(
                Note(
                    "Clase 1: Introducci\u00f3n a Jetpack Compose",
                    "Vimos los fundamentos de Compose, modificadores y primeros componentes.",
                    false,
                    LocalDate.of(2026, 4, 10),
                ),
                Note(
                    "Clase 2: Layouts y navegaci\u00f3n",
                    "Estudiamos Column, Row, Box y navegaci\u00f3n con NavHost.",
                    false,
                    LocalDate.of(2026, 4, 17),
                ),
                Note(
                    "Recordatorio TP",
                    "La fecha de entrega del TP se acerca. Revisar r\u00fabrica.",
                    true,
                    LocalDate.of(2026, 6, 25),
                ),
            ),
            absences = 0,
            studyHabits =
                generateStudyHabits(
                    LocalDate.of(2026, 3, 16),
                    LocalDate.of(2026, 7, 17),
                    listOf(
                        LocalDate.of(2026, 4, 22),
                        LocalDate.of(2026, 5, 20),
                        LocalDate.of(2026, 6, 30),
                        LocalDate.of(2026, 7, 1),
                        LocalDate.of(2026, 7, 13),
                    ),
                    seed = 1,
                ),
            grade = 8.5,
            color = CourseTeal,
        ),
        Course(
            "Matem\u00e1tica General",
            "01-6300",
            "Turno ma\u00f1ana, 8:00 a 12:00",
            "Ingenier\u00eda e Investigaciones Tecnol\u00f3gicas",
            CourseMode.VIRTUAL,
            listOf("Liliana Romano", "Federico Pileci"),
            listOf(
                LocalDate.of(2026, 5, 26),
                LocalDate.of(2026, 6, 18),
                LocalDate.of(2026, 7, 10),
            ),
            dummyNotesList,
            absences = 1,
            absenceDates = listOf(LocalDate.of(2026, 5, 26)),
            studyHabits =
                generateStudyHabits(
                    LocalDate.of(2026, 3, 16),
                    LocalDate.of(2026, 7, 17),
                    listOf(
                        LocalDate.of(2026, 5, 26),
                        LocalDate.of(2026, 6, 18),
                        LocalDate.of(2026, 7, 10),
                    ),
                    seed = 2,
                ),
            grade = 6.0,
            color = CourseBlue,
        ),
        Course(
            "Matem\u00e1tica General",
            "01-2900",
            "Turno noche, 19:00 a 23:00",
            "Ingenier\u00eda e Investigaciones Tecnol\u00f3gicas",
            CourseMode.SEMIPRESENCIAL,
            listOf("Liliana Romano", "Federico Pileci"),
            listOf(
                LocalDate.of(2026, 5, 26),
                LocalDate.of(2026, 6, 18),
                LocalDate.of(2026, 7, 10),
            ),
            listOf(
                Note(
                    "Clase 1: Repaso de funciones",
                    "Repasamos dominio, codominio, composici\u00f3n de funciones.",
                    false,
                    LocalDate.of(2026, 4, 12),
                ),
                Note(
                    "F\u00f3rmulas importantes",
                    "Derivadas b\u00e1sicas y reglas de integraci\u00f3n.",
                    true,
                    LocalDate.of(2026, 5, 3),
                ),
            ),
            absences = 2,
            absenceDates =
                listOf(
                    LocalDate.of(2026, 5, 26),
                    LocalDate.of(2026, 6, 18),
                ),
            studyHabits =
                generateStudyHabits(
                    LocalDate.of(2026, 3, 16),
                    LocalDate.of(2026, 7, 17),
                    listOf(
                        LocalDate.of(2026, 5, 26),
                        LocalDate.of(2026, 6, 18),
                        LocalDate.of(2026, 7, 10),
                    ),
                    seed = 3,
                ),
            grade = 4.0,
            color = CourseIndigo,
        ),
        Course(
            "Dise\u00f1o de Aplicaciones",
            "01-9900",
            "Turno noche, 19:00 a 23:00",
            "Ingenier\u00eda e Investigaciones Tecnol\u00f3gicas",
            CourseMode.SEMIPRESENCIAL,
            listOf("Juan Perez"),
            listOf(
                LocalDate.of(2026, 5, 26),
                LocalDate.of(2026, 6, 21),
                LocalDate.of(2026, 7, 15),
            ),
            listOf(
                Note(
                    "Mockups de la app",
                    "Dise\u00f1amos los wireframes en Figma para la navegaci\u00f3n principal.",
                    false,
                    LocalDate.of(2026, 5, 10),
                ),
                Note(
                    "Entrega final",
                    "La entrega incluye informe + c\u00f3digo fuente + presentaci\u00f3n.",
                    true,
                    LocalDate.of(2026, 7, 1),
                ),
            ),
            absences = 3,
            absenceDates =
                listOf(
                    LocalDate.of(2026, 5, 26),
                    LocalDate.of(2026, 6, 21),
                    LocalDate.of(2026, 7, 15),
                ),
            studyHabits =
                generateStudyHabits(
                    LocalDate.of(2026, 3, 16),
                    LocalDate.of(2026, 7, 17),
                    listOf(
                        LocalDate.of(2026, 5, 26),
                        LocalDate.of(2026, 6, 21),
                        LocalDate.of(2026, 7, 15),
                    ),
                    seed = 4,
                ),
            grade = 9.0,
            color = CoursePurple,
        ),
        Course(
            "Programaci\u00f3n Avanzada",
            "01-5100",
            "Turno tarde, 14:00 a 18:00",
            "Ingenier\u00eda e Investigaciones Tecnol\u00f3gicas",
            CourseMode.VIRTUAL,
            listOf("Marcela L\u00f3pez", "Diego Castro"),
            listOf(
                LocalDate.of(2026, 5, 12),
                LocalDate.of(2026, 6, 16),
                LocalDate.of(2026, 7, 7),
            ),
            listOf(
                Note(
                    "Patrones de dise\u00f1o",
                    "Vimos Singleton, Factory, Observer y su implementaci\u00f3n en Kotlin.",
                    false,
                    LocalDate.of(2026, 4, 20),
                ),
                Note(
                    "Concurrencia",
                    "Corrutinas, flujos y manejo de estados concurrentes.",
                    true,
                    LocalDate.of(2026, 5, 18),
                ),
                Note(
                    "Arquitectura limpia",
                    "Capas, casos de uso, repositorios e inyecci\u00f3n de dependencias.",
                    false,
                    LocalDate.of(2026, 6, 8),
                ),
            ),
            absences = 0,
            studyHabits =
                generateStudyHabits(
                    LocalDate.of(2026, 3, 16),
                    LocalDate.of(2026, 7, 17),
                    listOf(
                        LocalDate.of(2026, 5, 12),
                        LocalDate.of(2026, 6, 16),
                        LocalDate.of(2026, 7, 7),
                    ),
                    seed = 5,
                ),
            grade = 7.5,
            color = CourseOrange,
        ),
        Course(
            "Base de Datos",
            "01-7200",
            "Turno noche, 19:00 a 23:00",
            "Ingenier\u00eda e Investigaciones Tecnol\u00f3gicas",
            CourseMode.PRESENCIAL,
            listOf("Roberto Medina", "Carolina Acevedo"),
            listOf(
                LocalDate.of(2026, 5, 5),
                LocalDate.of(2026, 6, 9),
                LocalDate.of(2026, 7, 14),
            ),
            listOf(
                Note(
                    "Modelo entidad-relaci\u00f3n",
                    "Dise\u00f1amos el DER para el sistema de biblioteca.",
                    false,
                    LocalDate.of(2026, 4, 14),
                ),
                Note(
                    "SQL avanzado",
                    "JOINs, subconsultas, funciones de ventana y \u00edndices.",
                    true,
                    LocalDate.of(2026, 5, 12),
                ),
                Note(
                    "Normalizaci\u00f3n",
                    "Formas normales hasta 3FN con ejemplos pr\u00e1cticos.",
                    false,
                    LocalDate.of(2026, 6, 2),
                ),
            ),
            absences = 1,
            absenceDates = listOf(LocalDate.of(2026, 6, 9)),
            studyHabits =
                generateStudyHabits(
                    LocalDate.of(2026, 3, 16),
                    LocalDate.of(2026, 7, 17),
                    listOf(
                        LocalDate.of(2026, 5, 5),
                        LocalDate.of(2026, 6, 9),
                        LocalDate.of(2026, 7, 14),
                    ),
                    seed = 6,
                ),
            grade = 2.0,
            color = CourseTealDark,
        ),
        Course(
            "Ingl\u00e9s T\u00e9cnico",
            "01-1100",
            "Turno ma\u00f1ana, 8:00 a 10:00",
            "Departamento de Humanidades",
            CourseMode.PRESENCIAL,
            listOf("Laura Fern\u00e1ndez"),
            listOf(
                LocalDate.of(2026, 6, 2),
                LocalDate.of(2026, 6, 30),
            ),
            listOf(),
            absences = 0,
            studyHabits =
                generateStudyHabits(
                    LocalDate.of(2026, 3, 16),
                    LocalDate.of(2026, 7, 17),
                    importantDates = emptyList(),
                    seed = 7,
                ),
            grade = 5.5,
            color = CourseRed,
        ),
    )

@RequiresApi(Build.VERSION_CODES.O)
fun generateStudyHabits(
    start: LocalDate,
    end: LocalDate,
    importantDates: List<LocalDate>,
    seed: Int = 0,
): Map<LocalDate, Int> {
    val map = mutableMapOf<LocalDate, Int>()
    val random = Random(seed.toLong())
    var date = start
    while (!date.isAfter(end)) {
        var minutes: Int =
            if (random.nextFloat() < 0.35f) {
                when (random.nextInt(4)) {
                    0 -> 20
                    1 -> 30
                    2 -> 45
                    else -> 60
                }
            } else {
                0
            }

        importantDates.forEach { imp ->
            val daysUntil = ChronoUnit.DAYS.between(date, imp)
            if (daysUntil in 1..7) {
                minutes +=
                    when (daysUntil.toInt()) {
                        1 -> 60
                        2 -> 45
                        in 3..4 -> 30
                        else -> 15
                    }
            }
        }

        map[date] = minutes.coerceAtMost(180)
        date = date.plusDays(1)
    }
    return map
}

data class Course
    @RequiresApi(Build.VERSION_CODES.O)
    constructor(
        val name: String,
        val commission: String,
        val shiftAndSchedule: String,
        val departament: String,
        val mode: CourseMode,
        val teacher: List<String>,
        val importantDates: List<LocalDate>,
        val courseNotes: List<Note>,
        val absences: Int?,
        val absenceDates: List<LocalDate> = emptyList(),
        val semesterStart: LocalDate = LocalDate.of(2026, 3, 16),
        val semesterEnd: LocalDate = LocalDate.of(2026, 7, 17),
        val studyHabits: Map<LocalDate, Int> = emptyMap(),
        val grade: Double = 0.0,
        val color: Color,
    )
