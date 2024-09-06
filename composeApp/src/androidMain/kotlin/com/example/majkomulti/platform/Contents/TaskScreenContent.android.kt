package com.example.majkomulti.platform.Contents

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.majkomulti.components.AddButton
import com.example.majkomulti.components.SearchBox
import com.example.majkomulti.components.TaskCard
import com.example.majkomulti.images.MajkoResourceImages
import com.example.majkomulti.screen.task.TaskState
import com.example.majkomulti.screen.task.TaskViewModel
import com.example.majkomulti.screen.taskEditor.TaskEditorScreen
import com.example.majkomulti.strings.MajkoResourceStrings
import io.github.skeptick.libres.compose.painterResource
import kotlinx.coroutines.launch

internal actual class TaskScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { TaskViewModel() }
        val uiState by viewModel.stateFlow.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(Unit) {
            launch {
                viewModel.loadData()
                viewModel.loadSubData()
            }
        }

        Scaffold(
            topBar = {
                //панель при длинном нажатии
                if (uiState.isLongtap) {
                    LongTapPanel(viewModel::updateStatus, viewModel::removeTask,
                        uiState, viewModel::updateExpandedLongTap) }
                else {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(65.dp)
                            .padding(all = 10.dp)
                            .clip(RoundedCornerShape(30.dp))
                            .background(color = MaterialTheme.colorScheme.primary),
                        verticalAlignment = Alignment.CenterVertically) {

                        SearchBox(uiState.searchString, { viewModel.updateSearchString(it, 2) },
                            MajkoResourceStrings.task_search)

                        Column {
                            Row {
                                IconButton(
                                    onClick = { viewModel.updateExpandedFilter() },
                                    Modifier.size(27.dp)
                                ) {
                                    // Icon(painter = painterResource(R.drawable.icon_filter),
                                    //   contentDescription = "", tint = MaterialTheme.colorScheme.background)
                                }

                            }
                            //     FilterDropdown(expanded = uiState.expandedFilter, onDismissRequest = {  viewModel.updateExpandedFilter() },
                            //     R.string.filter_task_fav, { viewModel.updateSearchString(uiState.searchString, it) },
                            //   R.string.filter_task_each, R.string.filter_all)
                        }

                        Spacer(modifier = Modifier.width(5.dp))

                        IconButton(onClick = {
                            viewModel.updateSearchString(
                                uiState.searchString,
                                2
                            )
                        }, Modifier.size(27.dp)) {
                            //    Icon(painter = painterResource(R.drawable.icon_filter_off),
                            //  contentDescription = "", tint = MaterialTheme.colorScheme.background)
                        }
                    }
                }
            }
        ) {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Column(Modifier.fillMaxSize()) {
                    SetTaskScreen(viewModel, uiState, navigator)
                }

                Box(Modifier.align(Alignment.BottomEnd)) {
                    AddButton(onClick = { navigator.push(TaskEditorScreen()) }, id = "0")
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SetTaskScreen(viewModel: TaskViewModel, uiState: TaskState, navigator: Navigator) {
    val allTaskList = uiState.allTaskList
    val favoritesTaskList = uiState.favoritesTaskList

    Column(
        Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)) {

            if (!favoritesTaskList.isNullOrEmpty()) {
                item(span = { GridItemSpan(2) }) {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                        Text(text = MajkoResourceStrings.status_fav,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(start = 15.dp, top = 10.dp, bottom = 10.dp))
                    }
                }
                items(favoritesTaskList, key = {it.hashCode()}) { it ->
                    TaskCard(
                        navigator,
                        statusName = viewModel.getStatus(it.status),
                        priorityColor = viewModel.getPriority(it.priority),
                        taskData = it,
                        onBurnStarClick = { viewModel.removeFavotite(it) },
                        onDeadStarClick = { viewModel.addFavotite(it) },
                        onLongTap = { viewModel.openPanel(it) },
                        onLongTapRelease = { viewModel.openPanel(it) },
                        isSelected = uiState.longtapTaskId.contains(it.id),
                        modifier = Modifier.animateItemPlacement(animationSpec = tween(500))
                    )
                }
            }

            if (!allTaskList.isNullOrEmpty()) {
                item(span = { GridItemSpan(2) }) {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                        Text(text = MajkoResourceStrings.task_each,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(start = 15.dp, top = 10.dp, bottom = 10.dp))
                    }
                }
                items(allTaskList, key = {it.hashCode()}) { it ->
                    TaskCard(
                        navigator,
                        statusName = viewModel.getStatus(it.status),
                        priorityColor = viewModel.getPriority(it.priority),
                        taskData = it,
                        onBurnStarClick = { viewModel.removeFavotite(it) },
                        onDeadStarClick = { viewModel.addFavotite(it) },
                        onLongTap = { viewModel.openPanel(it) },
                        onLongTapRelease = { viewModel.openPanel(it) },
                        isSelected = uiState.longtapTaskId.contains(it.id),
                        modifier = Modifier.animateItemPlacement(animationSpec = tween(500))
                    )
                }
            }
        }
    }
}

@Composable
private fun LongTapPanel(onUpdateStatus: ()-> Unit, onRemoveTask: ()-> Unit,
                         uiState: TaskState, onExpandedLongTap: ()->Unit){
    Row(
        Modifier
            .fillMaxWidth()
            .height(65.dp)
            .background(color = MaterialTheme.colorScheme.secondary),
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End){


        Box(Modifier.padding(all = 10.dp)) {
            IconButton(onClick = {onExpandedLongTap() }) {
                Icon(painter = painterResource(MajkoResourceImages.icon_menu),
                    contentDescription = "", tint = MaterialTheme.colorScheme.background)
            }
            DropdownMenu(
                expanded = uiState.expandedLongTap,
                onDismissRequest = { onExpandedLongTap() },
                modifier = Modifier.fillMaxWidth(0.5f)) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clickable {
                            onUpdateStatus()
                            onExpandedLongTap()
                        }) {
                    Text(
                        MajkoResourceStrings.task_updatestatus,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(all = 10.dp)
                    )
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clickable {
                            onRemoveTask()
                            onExpandedLongTap()
                        }) {
                    Text(
                        MajkoResourceStrings.project_delite,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(all = 10.dp)
                    )
                }
            }
        }
    }
}