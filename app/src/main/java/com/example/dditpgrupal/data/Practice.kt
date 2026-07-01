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
            PracticeStatus.REVISION,
            true,
            listOf(
                "Tomas Gabriel Elbert",
                "Maximo Assad",
                @Suppress("ktlint:standard:max-line-length")
                "Rodrigo Roldan",
            ),
            grade = 6,
            reviewResponse = "El desarrollo es correcto pero faltan algunos fundamentos te\u00f3ricos. Revisar los apuntes de la unidad 2 y corregir las secciones se\u00f1aladas.",
            revisionDate = "15/07/2026",
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
            PracticeStatus.REVISION,
            true,
            listOf(
                "Tomas Gabriel Elbert",
                "Maximo Assad",
                @Suppress("ktlint:standard:max-line-length")
                "Rodrigo Roldan",
            ),
            grade = 5,
            reviewReason = "La conexi\u00f3n con la base de datos no funciona correctamente en el entorno de prueba. Revisar la configuraci\u00f3n de los controladores.",
            reviewResponse = "Corregir el archivo de configuraci\u00f3n y asegurarse de que las dependencias est\u00e9n actualizadas. La pr\u00f3xima entrega tiene fecha l\u00edmite el 20/07.",
            revisionDate = "20/07/2026",
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
    val grade: Int? = null,
    val newGrade: Int? = null,
    val reviewReason: String? = null,
    val reviewResponse: String? = null,
    val revisionDate: String? = null,
)
