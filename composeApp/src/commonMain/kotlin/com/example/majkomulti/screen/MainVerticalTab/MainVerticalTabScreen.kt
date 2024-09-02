package com.example.majkomulti.screen.MainVerticalTab

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.example.majkomulti.components.HorizontalLine
import com.example.majkomulti.screen.MainTab.ProfileTab
import com.example.majkomulti.screen.MainTab.TaskTab
import com.example.majkomulti.strings.MajkoResourceStrings

class MainVerticalTabScreen() : Screen {

    @Composable
    override fun Content() {
        TabNavigator(TaskTab, disposeNestedNavigators = true) { tab ->
            var isPersonal by remember{mutableStateOf(true)}
            val tabNavigator = LocalTabNavigator.current
            Row(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.secondary)) {

                Column(modifier = Modifier
                        .width(250.dp)
                        .fillMaxHeight()) {

                    TabNavItem(MyTaskTab)
                    TabNavItem(ProfileTab)
                    HorizontalLine(Modifier.padding(start = 10.dp, end = 10.dp, bottom = 0.dp).alpha(0.5f))
                    PersonalGroupButtons(isPersonal) {
                        isPersonal = !isPersonal
                        when(tabNavigator.current){
                            is PersonalTaskTab -> { tabNavigator.current = GroupTaskTab }
                            is PersonalProjectTab -> { tabNavigator.current = GroupProjectTab }
                            is PersonalGroupTab -> { tabNavigator.current = GroupGroupTab }
                            is GroupTaskTab -> { tabNavigator.current = PersonalTaskTab }
                            is GroupProjectTab -> { tabNavigator.current = PersonalProjectTab }
                            is GroupGroupTab -> { tabNavigator.current = PersonalGroupTab }
                        }
                    }

                    if(isPersonal){
                        TabNavItem(PersonalTaskTab)
                        TabNavItem(PersonalProjectTab)
                        TabNavItem(PersonalGroupTab)
                    }
                    else{
                        TabNavItem(GroupTaskTab)
                        TabNavItem(GroupProjectTab)
                        TabNavItem(GroupGroupTab)
                    }
                    HorizontalLine(Modifier.padding(start = 10.dp, end = 10.dp).alpha(0.5f))
                }

                Box(modifier = Modifier.fillMaxSize()) {
                    tab.current.Content()
                }
            }
        }
    }
}

@Composable
private fun PersonalGroupButtons(isPersonal: Boolean, onChange: () -> Unit){
    Row(Modifier.fillMaxWidth()
        .height(60.dp)
        .padding(top = 10.dp, start = 10.dp, end = 10.dp)
        .clip(RoundedCornerShape(10.dp))
        .background(MaterialTheme.colorScheme.secondaryContainer),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically){
        Row(modifier = Modifier
            .width(115.dp)
            .height(40.dp)
            .padding(start = 5.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable{onChange()}
            .background(if (isPersonal) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.secondaryContainer),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){
            Text(
                text = MajkoResourceStrings.project_personal_one,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                letterSpacing = 1.5.sp,
                color = (if (isPersonal) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.onSurface)
            )
        }

        Row(modifier = Modifier
            .width(115.dp)
            .height(40.dp)
            .padding(end = 5.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable{onChange()}
            .background(if (isPersonal) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.background),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically){

            Text(
                text = MajkoResourceStrings.project_group_one,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                letterSpacing = 1.5.sp,
                color = (if (isPersonal) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSecondary)
            )
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
        modifier = Modifier.fillMaxWidth().padding(top = 10.dp, start = 10.dp, end = 10.dp).clip(RoundedCornerShape(10.dp))
            .background(if(selected) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.secondary),
    ) {
        Row(Modifier.fillMaxWidth().padding(start = 5.dp), horizontalArrangement = Arrangement.Start){
            tab.options.icon?.let {
                Icon(painter = it, contentDescription = "", Modifier.size(25.dp),
                    tint = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSecondary)
            }
            Spacer(Modifier.width(15.dp))

            Text(
                text = tab.options.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 1.5.sp,
                color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}