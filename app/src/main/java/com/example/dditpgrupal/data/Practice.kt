package com.example.dditpgrupal.data

import com.example.dditpgrupal.data.enums.PracticeStatus

val dummyPracticeList =
    listOf(
        Practice("Diseño de formulario básico", PracticeStatus.PENDIENTE),
        Practice("Trabajo Práctico - Unidad 1", PracticeStatus.ENTREGADA),
        Practice(
            "Actividad en clase - Unidad 2",
            PracticeStatus.CORREGIDA,
            grade = 8,
            madeInGroup = true,
            groupMembers =
                listOf(
                    "Tomas Gabriel Elbert",
                    "Maximo Assad",
                    "Rodrigo Roldan",
                    "Camila Suárez",
                ),
        ),
        Practice("Prueba escrita", PracticeStatus.PENDIENTE),
        Practice("Actividad en clase - Unidad 3", PracticeStatus.PENDIENTE),
        Practice(
            "TP Integrador - Parte 1",
            PracticeStatus.SOLICITADA,
            grade = 6,
            reviewReason = "No estoy de acuerdo con la corrección, mi solución cumple con todos los requisitos planteados en la rúbrica.",
        ),
        Practice(
            "Ejercicio de estructuras",
            PracticeStatus.ACEPTADA,
            grade = 5,
            newGrade = 8,
            reviewResponse = "Tiene razón, se re-evaluó la entrega. La solución era correcta. Nueva nota: 8.",
        ),
        Practice(
            "Trabajo práctico grupal",
            PracticeStatus.RECHAZADA,
            grade = 7,
            reviewResponse = @Suppress("ktlint:standard:max-line-length")
            "La corrección se realizó correctamente según la rúbrica. No se encontraron errores en la evaluación. Nota final: 7.",
        ),
        Practice(
            "Ejercicio de integración",
            PracticeStatus.RECHAZADA,
            grade = 6,
            reviewResponse = @Suppress("ktlint:standard:max-line-length")
            "La solución presentada no cumple con los requisitos mínimos de la consigna. Se rechaza la solicitud de revisión. Nota final: 6.",
        ),
    )

data class Practice(
    val name: String,
    val status: PracticeStatus,
    val grade: Int? = null,
    val newGrade: Int? = null,
    val reviewReason: String? = null,
    val reviewResponse: String? = null,
    val revisionDate: String? = null,
    val madeInGroup: Boolean = false,
    val groupMembers: List<String> = listOf(),
)
