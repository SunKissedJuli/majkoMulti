package com.example.majkomulti.screen.MainTab

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator

class MainTabScreen(): Screen {

    @Composable
    override fun Content() {
        TabNavigator(TaskTab, disposeNestedNavigators = true){ tab ->
            Scaffold(
                bottomBar = {
                        BottomNavigation(
                            backgroundColor = MaterialTheme.colorScheme.background,
                            contentColor = MaterialTheme.colorScheme.onSecondary
                        ) {
                            TabNavItem(TaskTab)
                            TabNavItem(TaskTab)
                            TabNavItem(TaskTab)
                            TabNavItem(TaskTab)
                            TabNavItem(ProfileTab)
                        }
                }
            ){
                Box(modifier = Modifier.padding(it)){
                    tab.current.Content()
                }
            }
        }
    }

}

@Composable
private fun RowScope.TabNavItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    val selected = tabNavigator.current == tab
    BottomNavigationItem(
        label = { Text(text = tab.options.title, fontSize = 10.sp) },
        selected = tabNavigator.current == tab,
        selectedContentColor = MaterialTheme.colorScheme.primary,
        unselectedContentColor = MaterialTheme.colorScheme.onSecondary,
        onClick = {
            tabNavigator.current = tab
        },
       icon = {
            tab.options.icon?.let { painter ->
                Icon(painter, contentDescription = null)
            }
        }
    )
}