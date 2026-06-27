package com.example.dditpgrupal.data

import java.time.LocalDate

data class Message(
    val senderName: String,
    val subject: String,
    val preview: String,
    val date: LocalDate,
    val isRead: Boolean,
)

val dummyMessages =
    listOf(
        Message(
            "Heliana Vera",
            "Correcci\u00f3n TP Unidad 1",
            "Hola, les escribo para informarles que ya est\u00e1 disponible la correcci\u00f3n del trabajo pr\u00e1ctico...",
            LocalDate.of(2026, 6, 20),
            false,
        ),
        Message(
            "Gonzalo Rivas",
            "Material de clase - Unidad 3",
            "Se ha subido el material correspondiente a la Unidad 3 al campus virtual...",
            LocalDate.of(2026, 6, 18),
            false,
        ),
        Message(
            "Liliana Romano",
            "Recordatorio: Parcial",
            "Les recuerdo que el pr\u00f3ximo martes 26 de mayo se realizar\u00e1 el primer parcial...",
            LocalDate.of(2026, 5, 20),
            true,
        ),
        Message(
            "Heliana Vera",
            "Consulta sobre TP",
            "Buenas tardes, quer\u00eda consultarles si tienen alguna duda sobre el trabajo pr\u00e1ctico...",
            LocalDate.of(2026, 5, 15),
            true,
        ),
        Message(
            "Departamento de Alumnos",
            "Inscripci\u00f3n a cursada",
            "Se informa que el per\u00edodo de inscripci\u00f3n a cursadas del segundo cuatrimestre...",
            LocalDate.of(2026, 3, 1),
            true,
        ),
    )
