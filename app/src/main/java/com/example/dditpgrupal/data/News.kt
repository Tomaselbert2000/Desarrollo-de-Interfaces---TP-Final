package com.example.dditpgrupal.data

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

data class News(
    val title: String,
    val subtitle: String,
    val summary: String,
    val date: LocalDate,
    val category: String,
)

@RequiresApi(Build.VERSION_CODES.O)
val dummyNewsList =
    listOf(
        News(
            "La UNLaM abre inscripciones a becas universitarias 2026",
            "Conoc\u00e9 los requisitos y plazos para solicitar las becas del ciclo lectivo 2026",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            LocalDate.of(2026, 3, 10),
            "Becas",
        ),
        News(
            "Nuevo laboratorio de innovaci\u00f3n tecnol\u00f3gica en el Departamento de Ingenier\u00eda",
            "El espacio contar\u00e1 con equipamiento de \u00faltima generaci\u00f3n para proyectos de investigaci\u00f3n",
            "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            LocalDate.of(2026, 4, 5),
            "Institucional",
        ),
        News(
            "Calendario acad\u00e9mico 2026: fechas clave del segundo cuatrimestre",
            "Enterate de las fechas de inscripci\u00f3n, parciales y receso invernal",
            "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.",
            LocalDate.of(2026, 5, 20),
            "Acad\u00e9mico",
        ),
        News(
            "Investigadores de la UNLaM presentaron proyectos en congreso internacional",
            "Los trabajos fueron seleccionados entre m\u00e1s de 200 propuestas de todo el pa\u00eds",
            "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident.",
            LocalDate.of(2026, 6, 15),
            "Investigaci\u00f3n",
        ),
        News(
            "Talleres de extensi\u00f3n universitaria: abierta la inscripci\u00f3n para la comunidad",
            "Cursos gratuitos de oficios, arte y tecnolog\u00eda para todas las edades",
            "Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est.",
            LocalDate.of(2026, 6, 28),
            "Extensi\u00f3n",
        ),
        News(
            "Convenio de cooperaci\u00f3n con empresas tecnol\u00f3gicas del polo industrial",
            "El acuerdo permitir\u00e1 pasant\u00edas y proyectos conjuntos para estudiantes avanzados",
            "Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur.",
            LocalDate.of(2026, 7, 2),
            "Convenios",
        ),
        News(
            "Jornadas de puertas abiertas: la UNLaM recibe a futuros ingresantes",
            "Una oportunidad para conocer la oferta acad\u00e9mica y recorrer las instalaciones",
            "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat.",
            LocalDate.of(2026, 7, 10),
            "Ingreso",
        ),
        News(
            "Estudiantes de la UNLaM ganaron la hackat\u00f3n nacional de desarrollo",
            "El equipo conformado por alumnos de Ingenier\u00eda obtuvo el primer puesto",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco.",
            LocalDate.of(2026, 7, 18),
            "Estudiantes",
        ),
        News(
            "Nueva carrera de posgrado en Ciencia de Datos",
            "La especializaci\u00f3n comenzar\u00e1 en agosto con modalidad h\u00edbrida",
            "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident.",
            LocalDate.of(2026, 8, 1),
            "Posgrado",
        ),
        News(
            "La UNLaM implementa nuevo sistema de aulas virtuales",
            "La plataforma ofrece herramientas interactivas y acceso desde dispositivos m\u00f3viles",
            "Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est.",
            LocalDate.of(2026, 8, 15),
            "Tecnolog\u00eda",
        ),
    )
