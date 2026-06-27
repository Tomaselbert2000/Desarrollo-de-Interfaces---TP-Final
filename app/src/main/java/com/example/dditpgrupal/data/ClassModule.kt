package com.example.dditpgrupal.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Article
import androidx.compose.material.icons.filled.CoPresent
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.InsertChart
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.Quiz

val dummyModuleList =
    listOf(
        ClassModule(
            "M\u00f3dulo 1: Introducci\u00f3n",
            listOf(
                StudyMaterial("Gu\u00eda de estudio - Unidad 1", "PDF", Icons.Default.PictureAsPdf),
                StudyMaterial("Presentaci\u00f3n de materia", "PPT", Icons.Default.CoPresent),
                StudyMaterial("Clase Grabada - Introducci\u00f3n", "Video", Icons.Default.PlayCircle),
            ),
        ),
        ClassModule(
            "M\u00f3dulo 2: Conceptos avanzados",
            listOf(
                StudyMaterial("Gu\u00eda de estudio - Unidad 2", "PDF", Icons.Default.PictureAsPdf),
                StudyMaterial("Ejercicios de pr\u00e1ctica", "PDF", Icons.Default.Quiz),
                StudyMaterial("C\u00f3digo fuente de ejemplo", "C\u00f3digo", Icons.Default.Code),
            ),
        ),
        ClassModule(
            "M\u00f3dulo 3: Desarrollo pr\u00e1ctico",
            listOf(
                StudyMaterial("Gu\u00eda de laboratorio", "PDF", Icons.Default.Description),
                StudyMaterial("Plantilla de Trabajo", "Word", Icons.AutoMirrored.Filled.Article),
                StudyMaterial("Infograf\u00eda resumen", "PDF", Icons.Default.InsertChart),
            ),
        ),
        ClassModule(
            "M\u00f3dulo 4: Integraci\u00f3n",
            listOf(
                StudyMaterial("Gu\u00eda de estudio - Unidad 4", "PDF", Icons.Default.PictureAsPdf),
                StudyMaterial("Clase Grabada - Integraci\u00f3n", "Video", Icons.Default.PlayCircle),
                StudyMaterial("Presentaci\u00f3n de cierre", "PPT", Icons.Default.CoPresent),
            ),
        ),
        ClassModule(
            "Bibliograf\u00eda obligatoria",
            listOf(
                StudyMaterial("Libro: Fundamentos de programaci\u00f3n", "PDF", Icons.Default.PictureAsPdf),
                StudyMaterial("Paper: Arquitecturas modernas", "PDF", Icons.Default.Description),
            ),
        ),
        ClassModule(
            "Bibliograf\u00eda complementaria",
            listOf(
                StudyMaterial(
                    "Art\u00edculo: Buenas pr\u00e1cticas en UI",
                    "PDF",
                    Icons.AutoMirrored.Filled.Article,
                ),
                StudyMaterial("Video: Dise\u00f1o de interfaces", "Video", Icons.Default.PlayCircle),
            ),
        ),
        ClassModule(
            "Material de repaso",
            listOf(
                StudyMaterial("Ejercicios de pr\u00e1ctica", "PDF", Icons.Default.Quiz),
                StudyMaterial("C\u00f3digo fuente de ejemplo", "C\u00f3digo", Icons.Default.Code),
                StudyMaterial("Infograf\u00eda resumen", "PDF", Icons.Default.InsertChart),
            ),
        ),
        ClassModule(
            "Trabajo pr\u00e1ctico final",
            listOf(
                StudyMaterial("Consigna del TP", "PDF", Icons.Default.Description),
                StudyMaterial("Plantilla de entrega", "Word", Icons.AutoMirrored.Filled.Article),
                StudyMaterial("R\u00fabrica de evaluaci\u00f3n", "PDF", Icons.Default.Quiz),
            ),
        ),
    )

data class ClassModule(
    val moduleName: String,
    val content: List<StudyMaterial>,
)
