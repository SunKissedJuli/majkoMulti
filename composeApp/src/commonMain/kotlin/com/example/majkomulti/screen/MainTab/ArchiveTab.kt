package com.example.majkomulti.screen.MainTab

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.example.majkomulti.images.MajkoResourceImages
import com.example.majkomulti.screen.project.ProjectScreen
import io.github.skeptick.libres.compose.painterResource

object ArchiveTab: Tab {

    @Composable
    override fun Content() {
        Navigator(ProjectScreen())
    }

    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 1u,
            title = "Архив",
            icon = painterResource(MajkoResourceImages.icon_archive)
        )
}