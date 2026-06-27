package com.example.dditpgrupal.data

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

data class Message(
    val senderName: String,
    val subject: String,
    val preview: String,
    val date: LocalDate,
    val isRead: Boolean,
)

@RequiresApi(Build.VERSION_CODES.O)
val dummyMessages =
    listOf(
        Message(
            "Heliana Vera",
            "Correcci\u00f3n TP Unidad 1",
            "Hola, les escribo para informarles que ya est\u00e1 disponible la correcci\u00f3n del trabajo pr\u00e1ctico de la Unidad 1 en el campus virtual.",
            LocalDate.of(2026, 6, 20),
            false,
        ),
        Message(
            "Gonzalo Rivas",
            "Material de clase - Unidad 3",
            "Se ha subido el material correspondiente a la Unidad 3 al campus virtual. Por favor revisen la secci\u00f3n de materiales antes de la pr\u00f3xima clase.",
            LocalDate.of(2026, 6, 18),
            false,
        ),
        Message(
            "Liliana Romano",
            "Recordatorio: Parcial",
            "Les recuerdo que el pr\u00f3ximo martes 26 de mayo se realizar\u00e1 el primer parcial de Matem\u00e1tica General. Los temas incluyen funciones, l\u00edmites y derivadas.",
            LocalDate.of(2026, 5, 20),
            true,
        ),
        Message(
            "Heliana Vera",
            "Consulta sobre TP",
            "Buenas tardes, quer\u00eda consultarles si tienen alguna duda sobre el trabajo pr\u00e1ctico de la Unidad 2. La semana que viene haremos una clase de consulta.",
            LocalDate.of(2026, 5, 15),
            true,
        ),
        Message(
            "Departamento de Alumnos",
            "Inscripci\u00f3n a cursada",
            "Se informa que el per\u00edodo de inscripci\u00f3n a cursadas del segundo cuatrimestre de 2026 se extiende hasta el 15 de marzo.",
            LocalDate.of(2026, 3, 1),
            true,
        ),
        Message(
            "Marcela L\u00f3pez",
            "Nueva gu\u00eda de pr\u00e1ctica",
            "Sub\u00ed la gu\u00eda de pr\u00e1ctica de la Unidad 4 con ejercicios sobre patrones de dise\u00f1o. La revisaremos en la pr\u00f3xima clase.",
            LocalDate.of(2026, 6, 22),
            false,
        ),
        Message(
            "Roberto Medina",
            "Resultados del parcial",
            "Ya est\u00e1n publicadas las notas del parcial de Base de Datos. Pueden consultarlas en el campus. La revisi\u00f3n ser\u00e1 el jueves 18 a las 19:00.",
            LocalDate.of(2026, 6, 12),
            true,
        ),
        Message(
            "Biblioteca UNLaM",
            "Recordatorio de devoluci\u00f3n",
            "Tiene 2 libros en pr\u00e9stamo vencidos. Por favor acercarse a la biblioteca para renovarlos o devolverlos a la brevedad.",
            LocalDate.of(2026, 6, 8),
            true,
        ),
        Message(
            "Juan Perez",
            "Entrega final - Dise\u00f1o de Aplicaciones",
            "Recuerden que la entrega final incluye: informe en PDF, c\u00f3digo fuente en GitHub y presentaci\u00f3n de 10 minutos. Fecha l\u00edmite: 15/7.",
            LocalDate.of(2026, 6, 25),
            false,
        ),
        Message(
            "Centro de Estudiantes",
            "Asamblea general",
            "Se convoca a asamblea general el viernes 28/6 a las 18:00 en el aula 301 para tratar temas relacionados al calendario acad\u00e9mico.",
            LocalDate.of(2026, 6, 24),
            true,
        ),
    )
