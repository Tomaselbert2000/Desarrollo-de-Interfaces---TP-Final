package com.example.dditpgrupal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.dditpgrupal.sealedclass.NavigationRoute
import com.example.dditpgrupal.sealedclass.navigationRouteList
import com.example.dditpgrupal.ui.theme.DDITPGrupalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var currentNavigationRoute by remember { mutableStateOf(NavigationRoute.Home.route) }

            DDITPGrupalTheme {
                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            navigationRouteList.forEach { route ->
                                NavigationBarItem(
                                    icon = { Icon(route.icon, contentDescription = route.title) },
                                    label = { Text(text = route.title) },
                                    selected = currentNavigationRoute == route.route,
                                    onClick = {
                                        currentNavigationRoute = route.route
                                    },
                                )
                            }
                        }
                    },
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        when (currentNavigationRoute) {
                            NavigationRoute.Home.route -> {
                            }

                            NavigationRoute.Assignments.route -> {
                            }

                            NavigationRoute.Messages.route -> {
                            }

                            NavigationRoute.ProfileInfo.route -> {
                            }
                        }
                    }
                }
            }
        }
    }
}
