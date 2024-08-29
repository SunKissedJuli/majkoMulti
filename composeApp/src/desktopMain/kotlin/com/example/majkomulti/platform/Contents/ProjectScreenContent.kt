package com.example.majkomulti.platform.Contents

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.example.majkomulti.screen.project.ProjectViewModel


internal actual class ProjectScreen : Screen{

    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { ProjectViewModel() }

    }
}