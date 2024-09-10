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
import com.example.majkomulti.components.animatedItems
import com.example.majkomulti.strings.MajkoResourceStrings
import kotlinx.coroutines.launch

class PersonalTaskScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { TaskViewModel() }
        val uiState by viewModel.stateFlow.collectAsState()
        val navigator = LocalNavigator.currentOrThrow
        val favoritesTaskList = uiState.personalFavoritesTaskList

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
                    verticalAlignment = Alignment.CenterVertically) {
                    Row(
                        Modifier.fillMaxWidth(0.5f).height(40.dp).clip(RoundedCornerShape(30.dp))
                            .background(MaterialTheme.colorScheme.primary),
                        verticalAlignment = Alignment.CenterVertically){

                        SearchBox(uiState.searchString, { viewModel.updateSearchString(it) }, placeholder = MajkoResourceStrings.task_search)
                    }
                }

                LazyRow(
                    Modifier.fillMaxSize().padding(20.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    item {
                        LazyColumn(
                            Modifier.width(200.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            item {
                                Text(
                                    text =  MajkoResourceStrings.status_fav,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.padding(
                                        start = 10.dp,
                                        top = 5.dp,
                                        bottom = 5.dp
                                    )
                                )
                            }

                            animatedItems(favoritesTaskList) { favTask ->
                                TaskDesktopCard(
                                    {viewModel.openDesktopPanel(it)},
                                    statusName = viewModel.getStatus(favTask.status),
                                    priorityColor = viewModel.getPriority(favTask.priority),
                                    taskData = favTask,
                                    onLongTap = { viewModel.openPanel(it) },
                                    onLongTapRelease = { viewModel.openPanel(it) },
                                    isSelected = uiState.longtapTaskId.contains(favTask.id)
                                )
                            }
                        }

                        LazyTaskColumn({viewModel.openDesktopPanel(it)}, viewModel.filterByStatusPersonal(1), uiState,
                            viewModel::getStatus, { viewModel.getPriority(it) },  onBurnStarClick = { viewModel.removeFavotite(it) },
                            onDeadStarClick = { viewModel.addFavotite(it) }, MajkoResourceStrings.status_no)

                        LazyTaskColumn({viewModel.openDesktopPanel(it)}, viewModel.filterByStatusPersonal(2), uiState,
                            viewModel::getStatus, { viewModel.getPriority(it) }, onBurnStarClick = { viewModel.removeFavotite(it) },
                            onDeadStarClick = { viewModel.addFavotite(it) },  MajkoResourceStrings.status_discus)

                        LazyTaskColumn({viewModel.openDesktopPanel(it)}, viewModel.filterByStatusPersonal(3), uiState,
                            viewModel::getStatus, { viewModel.getPriority(it) }, onBurnStarClick = { viewModel.removeFavotite(it) },
                            onDeadStarClick = { viewModel.addFavotite(it) }, MajkoResourceStrings.status_wait)

                        LazyTaskColumn({viewModel.openDesktopPanel(it)}, viewModel.filterByStatusPersonal(4), uiState,
                            viewModel::getStatus, { viewModel.getPriority(it) },  onBurnStarClick = { viewModel.removeFavotite(it) },
                            onDeadStarClick = { viewModel.addFavotite(it) }, MajkoResourceStrings.status_process)

                        LazyTaskColumn({viewModel.openDesktopPanel(it)}, viewModel.filterByStatusPersonal(5), uiState,
                            viewModel::getStatus, { viewModel.getPriority(it) }, onBurnStarClick = { viewModel.removeFavotite(it) },
                            onDeadStarClick = { viewModel.addFavotite(it) }, MajkoResourceStrings.status_finish)

                    }
                }
            }
        }
    }
}