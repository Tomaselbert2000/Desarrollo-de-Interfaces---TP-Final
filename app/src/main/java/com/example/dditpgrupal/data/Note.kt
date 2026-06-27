package com.example.dditpgrupal.data

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
val dummyNotesList =
    listOf(
        Note(
            "Clase 1: Repaso de funciones",
            "Repasamos dominio, codominio, composici\u00f3n de funciones lineales y cuadr\u00e1ticas.",
            false,
            LocalDate.of(2026, 4, 12),
        ),
        Note(
            "Clase 2: L\u00edmites y continuidad",
            "Definici\u00f3n formal de l\u00edmite, propiedades, l\u00edmites laterales y discontinuidades.",
            false,
            LocalDate.of(2026, 4, 19),
        ),
        Note(
            "F\u00f3rmulas importantes para el parcial",
            "Derivadas b\u00e1sicas: polin\u00f3micas, trigonom\u00e9tricas, regla de la cadena. Integrales inmediatas.",
            true,
            LocalDate.of(2026, 5, 3),
        ),
        Note(
            "Clase 3: Derivadas",
            "Interpretaci\u00f3n geom\u00e9trica, reglas de derivaci\u00f3n, derivadas de orden superior.",
            false,
            LocalDate.of(2026, 4, 26),
        ),
        Note(
            "Clase 4: Aplicaciones de la derivada",
            "M\u00e1ximos y m\u00ednimos, optimizaci\u00f3n, teorema del valor medio.",
            false,
            LocalDate.of(2026, 5, 10),
        ),
        Note(
            "Recordatorio: fecha de parcial",
            "El parcial es el 26 de mayo. Temas: funciones, l\u00edmites y derivadas.",
            true,
            LocalDate.of(2026, 5, 17),
        ),
        Note(
            "Clase 5: Integrales",
            "Integral definida e indefinida, teorema fundamental del c\u00e1lculo, t\u00e9cnicas de integraci\u00f3n.",
            false,
            LocalDate.of(2026, 5, 24),
        ),
        Note(
            "Clase 6: Integrales avanzadas",
            "Integraci\u00f3n por partes, sustituci\u00f3n trigonom\u00e9trica, fracciones parciales.",
            false,
            LocalDate.of(2026, 5, 31),
        ),
        Note(
            "Resultados del parcial",
            "Notas publicadas. Revisi\u00f3n de ex\u00e1menes el pr\u00f3ximo jueves.",
            true,
            LocalDate.of(2026, 6, 7),
        ),
        Note(
            "Clase 7: Series y sucesiones",
            "Sucesiones mon\u00f3tonas, series convergentes, criterios de convergencia.",
            false,
            LocalDate.of(2026, 6, 14),
        ),
    )

data class Note(
    val name: String,
    val storedText: String,
    val isImportant: Boolean,
    val creationDate: LocalDate,
)
