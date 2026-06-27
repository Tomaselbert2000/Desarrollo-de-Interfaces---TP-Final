package com.example.dditpgrupal.data

import com.example.dditpgrupal.data.enums.PracticeStatus

val dummyPracticeList =
    listOf(
        Practice(
            "Diseño de formulario básico",
            PracticeStatus.PENDIENTE,
            false,
            listOf(
                "Tomas Gabriel Elbert",
                "Maximo Assad",
                @Suppress("ktlint:standard:max-line-length")
                "Rodrigo Roldan",
            ),
        ),
        Practice(
            "Trabajo Práctico - Unidad 1",
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
        Practice("Trabajo práctico individual", PracticeStatus.CORREGIDA, false),
    )

data class Practice(
    val name: String,
    val status: PracticeStatus,
    val madeInGroup: Boolean,
    val groupMembers: List<String> = listOf(),
)
