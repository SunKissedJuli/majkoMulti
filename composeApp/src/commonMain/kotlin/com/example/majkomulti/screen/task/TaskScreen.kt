package com.example.majkomulti.screen.task

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.example.majkomulti.platform.Contents.TaskScreenContent

class TaskScreen : Screen{
    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { TaskViewModel() }
        TaskScreenContent(viewModel)
    }

}