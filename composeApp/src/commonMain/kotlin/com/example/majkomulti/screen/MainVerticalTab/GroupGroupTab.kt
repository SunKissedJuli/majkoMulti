package com.example.majkomulti.screen.MainVerticalTab

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.example.majkomulti.images.MajkoResourceImages
import com.example.majkomulti.screen.group.PrivateGroupScreen
import com.example.majkomulti.screen.task.TaskScreen
import com.example.majkomulti.strings.MajkoResourceStrings
import io.github.skeptick.libres.compose.painterResource

object GroupGroupTab: Tab {

    @Composable
    override fun Content() {
        Navigator(PrivateGroupScreen())
    }

    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 1u,
            title = MajkoResourceStrings.common_group_group,
            icon = painterResource(MajkoResourceImages.icon_group)
        )
}