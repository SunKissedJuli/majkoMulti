package com.example.majkomulti.screen.MainTab

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.example.majkomulti.components.CustomScaffold
import com.example.majkomulti.screen.tabs.ProfileTab
import com.example.majkomulti.screen.tabs.TaskTab

class MainTabScreen(): Screen {

    @Composable
    override fun Content() {
        TabNavigator(TaskTab, disposeNestedNavigators = false){ tab ->
            CustomScaffold(
                bottomBar = {
                        NavigationBar(
                            containerColor = MaterialTheme.colorScheme.background,
                            contentColor = MaterialTheme.colorScheme.onSecondary
                        ) {
                            TabNavItem(GroupTab)
                            TabNavItem(ProjectTab)
                            TabNavItem(TaskTab)
                            TabNavItem(ArchiveTab)
                            TabNavItem(ProfileTab)
                        }
                }
            ){
                tab.current.Content()
            }
        }
    }
}

@Composable
private fun RowScope.TabNavItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    val selected = tabNavigator.current == tab
    NavigationBarItem(
        label = { Text(text = tab.options.title, fontSize = 10.sp) },
        selected = tabNavigator.current == tab,
        colors = NavigationBarItemColors(selectedIconColor = MaterialTheme.colorScheme.primary,
            selectedTextColor = MaterialTheme.colorScheme.primary,
            selectedIndicatorColor = Color.Transparent,
            unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
            unselectedTextColor = MaterialTheme.colorScheme.onSecondary,
            disabledTextColor = MaterialTheme.colorScheme.onSecondary,
            disabledIconColor = MaterialTheme.colorScheme.onSecondary),
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