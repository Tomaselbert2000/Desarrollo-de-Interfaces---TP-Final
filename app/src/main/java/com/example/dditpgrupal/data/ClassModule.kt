package com.example.dditpgrupal.data

val dummyModuleList =
    listOf(
        ClassModule("Introducción", dummyMaterials),
        ClassModule("Bibliografía obligatoria", dummyMaterials),
        ClassModule("Bibliografía complementaria", dummyMaterials),
    )

data class ClassModule(
    val moduleName: String,
    val content: List<StudyMaterial>,
)
