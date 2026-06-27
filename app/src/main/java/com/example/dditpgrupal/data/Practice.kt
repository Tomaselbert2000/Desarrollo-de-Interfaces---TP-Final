package com.example.dditpgrupal.data

import com.example.dditpgrupal.data.enums.PracticeStatus

val dummyPracticeList =
    listOf(
        Practice(
            "Dise\u00f1o de formulario b\u00e1sico",
            PracticeStatus.PENDIENTE,
            false,
            listOf(
                "Tomas Gabriel Elbert",
            ),
        ),
        Practice(
            "Trabajo Pr\u00e1ctico - Unidad 1",
            PracticeStatus.ENTREGADA,
            true,
            listOf(
                "Tomas Gabriel Elbert",
                "Maximo Assad",
                @Suppress("ktlint:standard:max-line-length")
                "Rodrigo Roldan",
            ),
        ),
        Practice(
            "Actividad en clase - Unidad 2",
            PracticeStatus.CORREGIDA,
            true,
            listOf(
                "Tomas Gabriel Elbert",
                "Maximo Assad",
                @Suppress("ktlint:standard:max-line-length")
                "Rodrigo Roldan",
            ),
        ),
        Practice(
            "Prueba escrita",
            PracticeStatus.PENDIENTE,
            true,
            listOf(
                "Tomas Gabriel Elbert",
                "Maximo Assad",
                @Suppress("ktlint:standard:max-line-length")
                "Rodrigo Roldan",
            ),
        ),
        Practice(
            "Actividad en clase - Unidad 3",
            PracticeStatus.PENDIENTE,
            true,
            listOf(
                "Tomas Gabriel Elbert",
                "Maximo Assad",
                @Suppress("ktlint:standard:max-line-length")
                "Rodrigo Roldan",
            ),
        ),
        Practice(
            "Trabajo pr\u00e1ctico individual",
            PracticeStatus.CORREGIDA,
            false,
            listOf(
                "Tomas Gabriel Elbert",
            ),
        ),
        Practice(
            "Ejercicios de l\u00f3gica proposicional",
            PracticeStatus.CORREGIDA,
            true,
            listOf(
                "Tomas Gabriel Elbert",
                "Maximo Assad",
                @Suppress("ktlint:standard:max-line-length")
                "Rodrigo Roldan",
                "Camila Su\u00e1rez",
            ),
        ),
        Practice(
            "Desarrollo de API REST",
            PracticeStatus.ENTREGADA,
            false,
            listOf(
                "Tomas Gabriel Elbert",
            ),
        ),
        Practice(
            "Integraci\u00f3n con base de datos",
            PracticeStatus.PENDIENTE,
            true,
            listOf(
                "Tomas Gabriel Elbert",
                "Maximo Assad",
                @Suppress("ktlint:standard:max-line-length")
                "Rodrigo Roldan",
            ),
        ),
        Practice(
            "Prueba de integraci\u00f3n continua",
            PracticeStatus.PENDIENTE,
            false,
            listOf(
                "Tomas Gabriel Elbert",
            ),
        ),
    )

data class Practice(
    val name: String,
    val status: PracticeStatus,
    val madeInGroup: Boolean,
    val groupMembers: List<String> = listOf(),
)
