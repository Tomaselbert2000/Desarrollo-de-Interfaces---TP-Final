package com.example.dditpgrupal.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import com.example.dditpgrupal.data.enums.CourseMode
import java.time.LocalDate

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
                Note("Recordatorio TP", "La fecha de entrega del TP se acerca. Revisar r\u00fabrica.", true, LocalDate.of(2026, 6, 25)),
            ),
            absences = 0,
            studentsAtRisk = null,
            color = Color(0xFF80CBC4),
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
            studentsAtRisk = 2,
            color = Color(0xFF64B5F6),
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
                Note("F\u00f3rmulas importantes", "Derivadas b\u00e1sicas y reglas de integraci\u00f3n.", true, LocalDate.of(2026, 5, 3)),
            ),
            absences = 2,
            studentsAtRisk = 3,
            color = Color(0xFF7986CB),
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
            studentsAtRisk = 5,
            color = Color(0xFFCE93D8),
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
                Note("Concurrencia", "Corrutinas, flujos y manejo de estados concurrentes.", true, LocalDate.of(2026, 5, 18)),
                Note(
                    "Arquitectura limpia",
                    "Capas, casos de uso, repositorios e inyecci\u00f3n de dependencias.",
                    false,
                    LocalDate.of(2026, 6, 8),
                ),
            ),
            absences = 0,
            studentsAtRisk = null,
            color = Color(0xFFFFB74D),
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
                Note("SQL avanzado", "JOINs, subconsultas, funciones de ventana y \u00edndices.", true, LocalDate.of(2026, 5, 12)),
                Note("Normalizaci\u00f3n", "Formas normales hasta 3FN con ejemplos pr\u00e1cticos.", false, LocalDate.of(2026, 6, 2)),
            ),
            absences = 1,
            studentsAtRisk = 1,
            color = Color(0xFF4DB6AC),
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
            studentsAtRisk = null,
            color = Color(0xFFEF9A9A),
        ),
    )

data class Course(
    val name: String,
    val commission: String,
    val shiftAndSchedule: String,
    val departament: String,
    val mode: CourseMode,
    val teacher: List<String>,
    val importantDates: List<LocalDate>,
    val courseNotes: List<Note>,
    val absences: Int?,
    val studentsAtRisk: Int?,
    val color: Color,
)
