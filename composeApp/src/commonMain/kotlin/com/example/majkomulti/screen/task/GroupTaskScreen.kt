package com.example.majkomulti.screen.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.majkomulti.components.LazyTaskColumn
import com.example.majkomulti.components.SearchBox
import com.example.majkomulti.components.TaskDesktopCard
import com.example.majkomulti.platform.Contents.TaskScreenContent
import com.example.majkomulti.strings.MajkoResourceStrings
import kotlinx.coroutines.launch

class GroupTaskScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { TaskViewModel() }
        val uiState by viewModel.stateFlow.collectAsState()
        val navigator = LocalNavigator.currentOrThrow
        val favoritesTaskList = uiState.groupFavoritesTaskList

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
                    Row(
                        Modifier.fillMaxWidth(0.5f).height(40.dp).clip(RoundedCornerShape(30.dp))
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

                        LazyTaskColumn(viewModel.filterByStatusGroup(1),
                            uiState,
                            navigator,
                            viewModel::getStatus,
                            { viewModel.getPriority(it) },
                            "Без статуса"
                        )

                        LazyTaskColumn(viewModel.filterByStatusGroup(2),
                            uiState,
                            navigator,
                            viewModel::getStatus,
                            { viewModel.getPriority(it) },
                            "Обсуждается"
                        )

                        LazyTaskColumn(viewModel.filterByStatusGroup(3),
                            uiState,
                            navigator,
                            viewModel::getStatus,
                            { viewModel.getPriority(it) },
                            "Ожидает"
                        )

                        LazyTaskColumn(viewModel.filterByStatusGroup(4),
                            uiState,
                            navigator,
                            viewModel::getStatus,
                            { viewModel.getPriority(it) },
                            "В процессе"
                        )

                        LazyTaskColumn(viewModel.filterByStatusGroup(5),
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
}