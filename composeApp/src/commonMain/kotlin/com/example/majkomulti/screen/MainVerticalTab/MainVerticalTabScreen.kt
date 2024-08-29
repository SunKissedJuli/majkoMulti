package com.example.majkomulti.screen.MainVerticalTab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.example.majkomulti.components.CustomScaffold
import com.example.majkomulti.screen.MainTab.ArchiveTab
import com.example.majkomulti.screen.MainTab.GroupTab
import com.example.majkomulti.screen.MainTab.ProfileTab
import com.example.majkomulti.screen.MainTab.ProjectTab
import com.example.majkomulti.screen.MainTab.TaskTab

class MainVerticalTabScreen() : Screen {

    @Composable
    override fun Content() {
        TabNavigator(TaskTab, disposeNestedNavigators = true) { tab ->
            Row(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.secondary)) {
                // Left Navigation Bar
                Column(
                    modifier = Modifier
                        .width(250.dp) // Set the width of the left bar
                        .fillMaxHeight()
                        .padding(top = 50.dp),
                  //  verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TabNavItem(GroupTab)
                    TabNavItem(ProjectTab)
                    TabNavItem(TaskTab)
                    TabNavItem(ArchiveTab)
                    TabNavItem(ProfileTab)
                }

                // Main Content Area
                Box(modifier = Modifier
                    .fillMaxSize()) {
                    tab.current.Content()
                }
            }
        }
    }
}

@Composable
private fun ColumnScope.TabNavItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    val selected = tabNavigator.current == tab
    TextButton(
        onClick = {
            tabNavigator.current = tab
        },
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(Modifier.fillMaxWidth().padding(start = 10.dp), horizontalArrangement = Arrangement.Start){
            tab.options.icon?.let {
                Icon(painter = it, contentDescription = "", Modifier.size(25.dp),
                    tint = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSecondary)
            }
            Spacer(Modifier.width(15.dp))

            Text(
                text = tab.options.title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}