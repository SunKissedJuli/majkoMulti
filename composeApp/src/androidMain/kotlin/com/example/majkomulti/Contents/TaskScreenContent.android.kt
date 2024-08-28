package com.example.majkomulti.platform.Contents

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.majkomulti.components.AddButton
import com.example.majkomulti.components.SearchBox
import com.example.majkomulti.components.TaskCard
import com.example.majkomulti.screen.task.TaskState
import com.example.majkomulti.screen.task.TaskViewModel
import kotlinx.coroutines.launch

@Composable
internal actual fun TaskScreenContent(viewModel: TaskViewModel){

    val uiState by viewModel.stateFlow.collectAsState()

    val navigator = LocalNavigator.currentOrThrow

    LaunchedEffect(Unit) {
        launch {
            viewModel.loadData()
        }
    }

    Scaffold (
        topBar = {

            //панель при длинном нажатии
          // if (uiState.isLongtap) {
            //    LongTapPanel(viewModel::updateStatus, viewModel::removeTask, uiState, viewModel::updateExpandedLongTap )
          //  } else {

                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(65.dp)
                        .padding(all = 10.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .background(color = MaterialTheme.colorScheme.primary),
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    SearchBox(uiState.searchString, { viewModel.updateSearchString(it, 2) },"Искать в задачах...")

                    Column {
                        Row {
                            IconButton(onClick = { viewModel.updateExpandedFilter() }, Modifier.size(27.dp)) {
                               // Icon(painter = painterResource(R.drawable.icon_filter),
                                 //   contentDescription = "", tint = MaterialTheme.colorScheme.background)
                            }

                        }
                   //     FilterDropdown(expanded = uiState.expandedFilter, onDismissRequest = {  viewModel.updateExpandedFilter() },
                       //     R.string.filter_task_fav, { viewModel.updateSearchString(uiState.searchString, it) },
                         //   R.string.filter_task_each, R.string.filter_all)
                    }

                    Spacer(modifier = Modifier.width(5.dp))

                    IconButton(onClick = { viewModel.updateSearchString(uiState.searchString, 2) }, Modifier.size(27.dp)) {
                    //    Icon(painter = painterResource(R.drawable.icon_filter_off),
                          //  contentDescription = "", tint = MaterialTheme.colorScheme.background)
                    }
                }
            }
      //  }
    ){
        Box(
            Modifier
                .fillMaxSize()
                .padding(it)
                .background(MaterialTheme.colorScheme.background)) {
            Column(Modifier.fillMaxSize()) {
                SetTaskScreen(viewModel, uiState, navigator)
            }

            Box(Modifier.align(Alignment.BottomEnd)){
                AddButton(onClick = {}, id = "0")
            }
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
private fun SetTaskScreen(viewModel: TaskViewModel, uiState: TaskState, navigator: Navigator) {
    val allTaskList = uiState.searchAllTaskList
    val favoritesTaskList = uiState.searchFavoritesTaskList

    Column(
        Modifier
        .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top) {

        LazyColumn(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)) {

            if (!favoritesTaskList.isNullOrEmpty()) {
                item {
                    Text(
                        text = "Избранные",
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(start = 15.dp, top = 10.dp, bottom = 10.dp)
                    )
                }
                items(favoritesTaskList.size / 2 + favoritesTaskList.size % 2) { rowIndex ->
                    Row(Modifier.fillMaxWidth()) {
                        val firstIndex = rowIndex * 2
                        if (firstIndex < favoritesTaskList.size) {
                            Column(Modifier.fillMaxWidth(0.5f)) {
                                TaskCard(navigator,
                                    statusName = viewModel.getStatus(favoritesTaskList[firstIndex].status),
                                    priorityColor = viewModel.getPriority(favoritesTaskList[firstIndex].priority),
                                    taskData = favoritesTaskList[firstIndex],
                                    onLongTap = { viewModel.openPanel(it) },
                                    onLongTapRelease = { viewModel.openPanel(it) },
                                    isSelected = uiState.longtapTaskId.contains(favoritesTaskList[firstIndex].id)
                                )
                            }

                        }

                        val secondIndex = firstIndex + 1
                        if (secondIndex < favoritesTaskList.size) {
                            Column(Modifier.fillMaxWidth()) {
                                TaskCard(navigator,
                                    statusName = viewModel.getStatus(favoritesTaskList[secondIndex].status),
                                    priorityColor = viewModel.getPriority(favoritesTaskList[secondIndex].priority),
                                    taskData = favoritesTaskList[secondIndex],
                                    onLongTap = { viewModel.openPanel(it) },
                                    onLongTapRelease = { viewModel.openPanel(it) },
                                    isSelected = uiState.longtapTaskId.contains(favoritesTaskList[secondIndex].id)
                                )
                            }

                        }
                    }
                }
            }

            if (!allTaskList.isNullOrEmpty()) {
                item {
                    Text(
                        text = "Другие",
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(start = 15.dp, top = 10.dp, bottom = 10.dp)
                    )
                }
                items(allTaskList.size / 2 + allTaskList.size % 2) { rowIndex ->
                    Row(Modifier.fillMaxWidth()) {
                        val firstIndex = rowIndex * 2
                        if (firstIndex < allTaskList.size) {
                            Column(Modifier.fillMaxWidth(0.5f)) {
                                TaskCard(navigator,
                                    statusName = viewModel.getStatus(allTaskList[firstIndex].status),
                                    priorityColor = viewModel.getPriority(allTaskList[firstIndex].priority),
                                    taskData = allTaskList[firstIndex],
                                    onLongTap = { viewModel.openPanel(it) },
                                    onLongTapRelease = { viewModel.openPanel(it) },
                                    isSelected = uiState.longtapTaskId.contains(allTaskList[firstIndex].id)
                                )
                            }

                        }

                        val secondIndex = firstIndex + 1
                        if (secondIndex < allTaskList.size) {
                            Column(Modifier.fillMaxWidth()) {
                                TaskCard(navigator,
                                    statusName = viewModel.getStatus(allTaskList[secondIndex].status),
                                    priorityColor = viewModel.getPriority(allTaskList[secondIndex].priority),
                                    taskData = allTaskList[secondIndex],
                                    onLongTap = { viewModel.openPanel(it) },
                                    onLongTapRelease = { viewModel.openPanel(it) },
                                    isSelected = uiState.longtapTaskId.contains(allTaskList[secondIndex].id)
                                )
                            }
                        }
                    }
                }
            }
        }

    }
}

/*@Composable
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
                Icon(painter = painterResource(R.drawable.icon_menu),
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
                        stringResource(R.string.task_updatestatus),
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
                        stringResource(R.string.project_delite),
                        fontSize = 18.sp,
                        modifier = Modifier.padding(all = 10.dp)
                    )
                }
            }
        }
    }
}*/