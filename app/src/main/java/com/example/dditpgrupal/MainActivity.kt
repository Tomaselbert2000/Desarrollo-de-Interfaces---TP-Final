package com.example.dditpgrupal

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dditpgrupal.data.dummyCourseList
import com.example.dditpgrupal.data.dummyNewsList
import com.example.dditpgrupal.sealedclass.navigationRouteList
import com.example.dditpgrupal.ui.components.CourseMenu
import com.example.dditpgrupal.ui.screens.CourseScreen
import com.example.dditpgrupal.ui.screens.HomeScreen
import com.example.dditpgrupal.ui.screens.LoginScreen
import com.example.dditpgrupal.ui.screens.MessageScreen
import com.example.dditpgrupal.ui.screens.NewsDetailScreen
import com.example.dditpgrupal.ui.screens.PracticeFilterScreen
import com.example.dditpgrupal.ui.screens.ProfileEditionScreen
import com.example.dditpgrupal.ui.screens.ProfileScreen
import com.example.dditpgrupal.ui.screens.ScheduleScreen
import com.example.dditpgrupal.ui.theme.DDITPGrupalTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DDITPGrupalTheme {
                AppNavigation()
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val mainRoutes = navigationRouteList.map { it.route }
    val showBottomBar = currentRoute in mainRoutes

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                ) {
                    navigationRouteList.forEach { route ->
                        NavigationBarItem(
                            icon = { Icon(route.icon, contentDescription = route.title) },
                            label = { Text(text = route.title) },
                            selected = currentRoute == route.route,
                            colors =
                                NavigationBarItemDefaults.colors(
                                    selectedIconColor = MaterialTheme.colorScheme.primary,
                                    selectedTextColor = MaterialTheme.colorScheme.primary,
                                    indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                ),
                            onClick = {
                                if (currentRoute != route.route) {
                                    navController.navigate(route.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                        )
                    }
                }
            }
        },
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(
                navController = navController,
                startDestination = "login",
                enterTransition = { fadeIn() + slideInHorizontally { it / 4 } },
                exitTransition = { fadeOut() + slideOutHorizontally { -it / 4 } },
                popEnterTransition = { fadeIn() + slideInHorizontally { -it / 4 } },
                popExitTransition = { fadeOut() + slideOutHorizontally { it / 4 } },
            ) {
                composable("login") {
                    LoginScreen(
                        onLogin = {
                            navController.navigate("home") {
                                popUpTo("login") { inclusive = true }
                            }
                        },
                    )
                }
                composable("home") {
                    HomeScreen(
                        onNewsClick = { news ->
                            val index = dummyNewsList.indexOf(news)
                            navController.navigate("news/$index")
                        },
                        onProfileClick = { navController.navigate("profile") },
                        onCoursesClick = { navController.navigate("courses") },
                        onPracticesClick = { navController.navigate("practice_list") },
                        onEventsClick = { navController.navigate("schedule/0") },
                    )
                }
                composable("courses") {
                    CourseScreen(
                        onCourseClick = { index ->
                            navController.navigate("course/$index")
                        },
                    )
                }
                composable(
                    route = "course/{courseIndex}",
                    arguments = listOf(navArgument("courseIndex") { type = NavType.IntType }),
                ) { backStackEntry ->
                    val courseIndex = backStackEntry.arguments?.getInt("courseIndex") ?: 0
                    CourseMenu(
                        course = dummyCourseList[courseIndex],
                        onBackClick = { navController.popBackStack() },
                    )
                }
                composable("messages") {
                    MessageScreen()
                }
                composable(
                    route = "news/{newsIndex}",
                    arguments = listOf(navArgument("newsIndex") { type = NavType.IntType }),
                ) { backStackEntry ->
                    val newsIndex = backStackEntry.arguments?.getInt("newsIndex") ?: 0
                    NewsDetailScreen(
                        news = dummyNewsList[newsIndex],
                        onBackClick = { navController.popBackStack() },
                    )
                }
                composable("profile") {
                    ProfileScreen(
                        onEditClick = { navController.navigate("profile_edit") },
                    )
                }
                composable("profile_edit") {
                    ProfileEditionScreen(
                        onBackClick = { navController.popBackStack() },
                        onSaveClick = { navController.popBackStack() },
                    )
                }
                composable("practice_list") {
                    PracticeFilterScreen(
                        onBackClick = { navController.popBackStack() },
                    )
                }
                composable(
                    route = "schedule/{courseIndex}",
                    arguments = listOf(navArgument("courseIndex") { type = NavType.IntType }),
                ) { backStackEntry ->
                    val courseIndex = backStackEntry.arguments?.getInt("courseIndex") ?: 0
                    ScheduleScreen(course = dummyCourseList[courseIndex])
                }
            }
        }
    }
}
