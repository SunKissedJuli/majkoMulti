package com.example.majkomulti.platform.Contents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.majkomulti.components.SearchBox
import com.example.majkomulti.components.TaskCard
import com.example.majkomulti.components.TaskDesktopCard
import com.example.majkomulti.domain.modelsUI.Task.TaskDataUi
import com.example.majkomulti.screen.profile.ProfileScreen
import com.example.majkomulti.screen.task.TaskState
import com.example.majkomulti.screen.task.TaskViewModel
import com.example.majkomulti.strings.MajkoResourceStrings
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource


@Composable
internal actual fun TaskScreenContent(viewModel: TaskViewModel) {
    val uiState by viewModel.stateFlow.collectAsState()
    val navigator = LocalNavigator.currentOrThrow
    val favoritesTaskList = uiState.searchFavoritesTaskList

    LaunchedEffect(Unit) {
        launch {
            viewModel.loadData()
        }
    }

    Row(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {

        Column(Modifier.fillMaxSize()) {
            Row(
                Modifier.fillMaxWidth().height(60.dp)
                    .background(MaterialTheme.colorScheme.onSecondaryContainer),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(Modifier.fillMaxWidth(0.5f).height(40.dp).clip(RoundedCornerShape(30.dp))
                    .background(MaterialTheme.colorScheme.primary),
                    verticalAlignment = Alignment.CenterVertically){
                   // if(!uiState.allTaskList.isNullOrEmpty()){
                        SearchBox(uiState.searchString, { viewModel.updateSearchString(it, 2) },
                            placeholder = MajkoResourceStrings.task_search
                        )
                   // }
                }

            }

            LazyRow(
                Modifier.fillMaxSize().padding(20.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    LazyColumn(
                        Modifier.width(200.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        item {
                            Text(
                                text = "Избранные",
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(
                                    start = 10.dp,
                                    top = 5.dp,
                                    bottom = 5.dp
                                )
                            )
                        }
                        if (favoritesTaskList != null) {
                            items(favoritesTaskList.size) { index ->
                                TaskDesktopCard(
                                    navigator,
                                    statusName = viewModel.getStatus(favoritesTaskList[index].status),
                                    priorityColor = viewModel.getPriority(favoritesTaskList[index].priority),
                                    taskData = favoritesTaskList[index],
                                    onLongTap = { viewModel.openPanel(it) },
                                    onLongTapRelease = { viewModel.openPanel(it) },
                                    isSelected = uiState.longtapTaskId.contains(favoritesTaskList[index].id)
                                )
                            }
                        }
                    }

                    LazyTaskColumn(viewModel.filterByStatus(1),
                        uiState,
                        navigator,
                        viewModel::getStatus,
                        { viewModel.getPriority(it) },
                        "Без статуса"
                    )

                    LazyTaskColumn(viewModel.filterByStatus(2),
                        uiState,
                        navigator,
                        viewModel::getStatus,
                        { viewModel.getPriority(it) },
                        "Обсуждается"
                    )

                    LazyTaskColumn(viewModel.filterByStatus(3),
                        uiState,
                        navigator,
                        viewModel::getStatus,
                        { viewModel.getPriority(it) },
                        "Ожидает"
                    )

                    LazyTaskColumn(viewModel.filterByStatus(4),
                        uiState,
                        navigator,
                        viewModel::getStatus,
                        { viewModel.getPriority(it) },
                        "В процессе"
                    )

                    LazyTaskColumn(viewModel.filterByStatus(5),
                        uiState,
                        navigator,
                        viewModel::getStatus,
                        { viewModel.getPriority(it) },
                        "Завершена"
                    )

                }
            }
        }
    }
}

@Composable
private fun LazyTaskColumn(
    taskList: List<TaskDataUi>,
    uiState: TaskState,
    navigator: Navigator,
    status: (Int)-> String,
    color: @Composable (Int)-> Color,
    text: String){
    Spacer(Modifier.width(15.dp))

    LazyColumn(Modifier.width(200.dp),
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
            items(taskList.size){ index ->
                TaskDesktopCard(navigator,
                    statusName = status(taskList[index].status),
                    priorityColor = color(taskList[index].priority),
                    taskData = taskList[index],
                    onLongTap = { },
                    onLongTapRelease = { },
                    isSelected = uiState.longtapTaskId.contains(taskList[index].id))
            }
        }
    }
}