package com.example.majkomulti.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.majkomulti.domain.modelsUI.Task.TaskDataUi
import com.example.majkomulti.screen.task.TaskState

@Composable
fun LazyTaskColumn(
    onClick: (String) -> Unit = {},
    taskList: List<TaskDataUi>,
    uiState: TaskState,
    status: (Int)-> String,
    color: @Composable (Int)-> Color,
    onBurnStarClick: (String) -> Unit = {},
    onDeadStarClick: (String) -> Unit = {},
    text: String){
    Spacer(Modifier.width(15.dp))

    LazyColumn(
        Modifier.width(200.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)){
        item {
            Text(text = text,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(start = 10.dp,
                    top = 5.dp,
                    bottom = 5.dp))
        }
        if (taskList.isNotEmpty()) {
            animatedItems(taskList){ task ->
                TaskDesktopCard(
                    onClick = onClick,
                    statusName = status(task.status),
                    priorityColor = color(task.priority),
                    taskData = task,
                    onBurnStarClick = onBurnStarClick,
                    onDeadStarClick = onDeadStarClick,
                    onLongTap = { },
                    onLongTapRelease = { },
                    isSelected = uiState.longtapTaskId.contains(task.id))
            }
        }
    }
}