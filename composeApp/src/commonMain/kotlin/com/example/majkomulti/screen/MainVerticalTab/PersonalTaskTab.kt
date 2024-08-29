package com.example.majkomulti.screen.MainVerticalTab

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.example.majkomulti.images.MajkoResourceImages
import com.example.majkomulti.screen.task.TaskScreen
import com.example.majkomulti.strings.MajkoResourceStrings
import io.github.skeptick.libres.compose.painterResource

object PersonalTaskTab: Tab {

    @Composable
    override fun Content() {
        Navigator(TaskScreen())
    }

    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 1u,
            title = MajkoResourceStrings.common_personal_task,
            icon = painterResource(MajkoResourceImages.icon_task)
        )
}