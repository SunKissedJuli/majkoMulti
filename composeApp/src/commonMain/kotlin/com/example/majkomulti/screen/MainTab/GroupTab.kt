package com.example.majkomulti.screen.MainTab

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.example.majkomulti.images.MajkoResourceImages
import com.example.majkomulti.platform.Contents.ProjectScreen
import io.github.skeptick.libres.compose.painterResource

object GroupTab: Tab {

    @Composable
    override fun Content() {
        Navigator(ProjectScreen())
    }

    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 1u,
            title = "Группы",
            icon = painterResource(MajkoResourceImages.icon_group)
        )
}