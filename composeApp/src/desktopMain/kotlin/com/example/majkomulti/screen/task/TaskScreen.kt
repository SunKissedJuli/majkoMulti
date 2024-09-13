package com.example.majkomulti.screen.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import coil3.compose.SubcomposeAsyncImage
import com.example.majkomulti.commons.Constantas
import com.example.majkomulti.ext.clickableBlank
import com.example.majkomulti.components.BlueRoundedButton
import com.example.majkomulti.components.ButtonBack
import com.example.majkomulti.components.HorizontalLine
import com.example.majkomulti.components.LazyTaskColumn
import com.example.majkomulti.components.SearchBox
import com.example.majkomulti.components.SimpleTextField
import com.example.majkomulti.components.SpinnerSampleDesktop
import com.example.majkomulti.components.TaskDesktopCard
import com.example.majkomulti.components.animatedItems
import com.example.majkomulti.strings.MajkoResourceStrings
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale


internal actual class TaskScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { TaskViewModel() }
        val uiState by viewModel.stateFlow.collectAsState()
        val favoritesTaskList = uiState.favoritesTaskList

        LaunchedEffect(Unit) {
            launch {
                viewModel.loadData()
                viewModel.loadSubData()
            }
        }

        Box(Modifier.fillMaxSize()) {
            Row(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {

                Column(Modifier.fillMaxSize()) {
                    Row(Modifier.fillMaxWidth().height(60.dp)
                        .background(MaterialTheme.colorScheme.onSecondaryContainer),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically) {

                        Row(Modifier.fillMaxWidth(0.5f).height(40.dp).clip(RoundedCornerShape(30.dp))
                            .background(MaterialTheme.colorScheme.primary),
                            verticalAlignment = Alignment.CenterVertically) {

                            SearchBox(uiState.searchString, { viewModel.updateSearchString(it) },
                                placeholder = MajkoResourceStrings.task_search)
                        }
                    }

                    LazyRow(Modifier.fillMaxSize().padding(20.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        item {
                            LazyColumn(Modifier.width(200.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(5.dp)) {
                                item {
                                    Text(text = MajkoResourceStrings.status_fav,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        modifier = Modifier.padding(start = 10.dp, top = 5.dp, bottom = 5.dp))
                                }

                                animatedItems(items = favoritesTaskList,) { favTask ->
                                    TaskDesktopCard(
                                        onClick = { viewModel.openDesktopPanel(it) },
                                        statusName = viewModel.getStatus(favTask.status),
                                        priorityColor = viewModel.getPriority(favTask.priority),
                                        taskData = favTask,
                                        onBurnStarClick = { viewModel.removeFavotite(it) },
                                        onDeadStarClick = { viewModel.addFavotite(it) },
                                        onLongTap = { viewModel.openPanel(it) },
                                        onLongTapRelease = { viewModel.openPanel(it) },
                                        isSelected = uiState.longtapTaskId.contains(favTask.id))
                                }
                            }

                            for(status in uiState.statuses){
                                LazyTaskColumn({ viewModel.openDesktopPanel(it) }, viewModel.filterByStatus(status.id),
                                    uiState,viewModel::getStatus, { viewModel.getPriority(it) },
                                    onBurnStarClick = { viewModel.removeFavotite(it) },
                                    onDeadStarClick = { viewModel.addFavotite(it) },status.name)
                            }
                        }
                    }
                }
            }

            if (uiState.isTask && uiState.taskData.text.isNotEmpty()) {
                Column(Modifier.fillMaxHeight().fillMaxWidth(0.45f).align(Alignment.TopEnd)
                    .background(MaterialTheme.colorScheme.secondary)
                    .clickableBlank { }) {

                    Row(Modifier.fillMaxWidth().height(50.dp),
                        verticalAlignment = Alignment.CenterVertically) {
                        ButtonBack(onClick = viewModel::openDesktopPanel)
                    }
                    HorizontalLine()

                    Column(Modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp, )) {
                        SimpleTextField(uiState.taskData.title, viewModel::updateTitle, style = androidx.compose.ui.text.TextStyle(
                                fontWeight = FontWeight.Bold, fontSize = 20.sp))

                        Spacer(Modifier.height(20.dp))
                        Row {
                            Text(text = MajkoResourceStrings.task_autor,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.fillMaxWidth(0.5f))

                            SubcomposeAsyncImage((Constantas.BASE_URL + uiState.taskData.creator.get(0).image),
                                contentDescription = "",
                                Modifier.size(27.dp).clip(CircleShape),
                                contentScale = ContentScale.Crop,
                                error = {
                                    Box(Modifier.fillMaxHeight(0.8f).size(27.dp).aspectRatio(1f)
                                        .background(MaterialTheme.colorScheme.primary, shape = CircleShape)) })

                            Spacer(Modifier.width(10.dp))
                            Text(text = uiState.taskData.creator.get(0).name)
                        }
                        Spacer(Modifier.height(10.dp))

                        Row {
                            Text(text = MajkoResourceStrings.task_project,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.fillMaxWidth(0.5f))
                        }
                        Spacer(Modifier.height(15.dp))

                        Row {
                            Text(text = MajkoResourceStrings.task_data,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.fillMaxWidth(0.5f))

                            val formatter = DateTimeFormatter.ISO_DATE_TIME
                            val dateTime =
                                LocalDateTime.parse(uiState.taskData.createdAt, formatter)
                            Text(text = dateTime.dayOfWeek.getDisplayName(
                                    TextStyle.SHORT, Locale("ru")) + ", " + dateTime.dayOfMonth + " "
                                        + dateTime.month.getDisplayName(TextStyle.FULL, Locale("ru")))
                        }
                        Spacer(Modifier.height(15.dp))

                        Row {
                            Text(text = MajkoResourceStrings.task_deadline,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.fillMaxWidth(0.5f))

                            val dateTime = LocalDateTime.parse(uiState.taskData.deadline,
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                            Text(text = dateTime.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale("ru"))
                                    + ", " + dateTime.dayOfMonth + " " + dateTime.month
                                        .getDisplayName(TextStyle.FULL, Locale("ru")))
                        }
                        Spacer(Modifier.height(15.dp))

                        Row {
                            Text(text = MajkoResourceStrings.task_prioritet,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.fillMaxWidth(0.5f))

                            SpinnerSampleDesktop(items = viewModel.getPriority(),
                                selectedItem = viewModel.getPriorityName(uiState.taskData.priority), {viewModel.updateTaskPriority(it)})
                        }
                        Spacer(Modifier.height(15.dp))

                        Row {
                            Text(text = MajkoResourceStrings.task_status,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.fillMaxWidth(0.5f))

                            SpinnerSampleDesktop(items = viewModel.getStatus(),
                                selectedItem = viewModel.getStatusName(uiState.taskData.status), {viewModel.updateTaskStatus(it) })
                        }
                        Spacer(Modifier.height(15.dp))

                        LazyRow(Modifier.fillMaxWidth()){
                            if(uiState.taskData.countFiles>0){
                                items(uiState.taskData.files){ file ->
                                    SubcomposeAsyncImage((file.link),
                                        contentDescription = "",
                                        Modifier.height(90.dp).clip(RoundedCornerShape(10.dp)),
                                        contentScale = ContentScale.Crop,
                                        error = {
                                            if(file.link.endsWith(".pdf")){
                                                Row(Modifier.height(90.dp).width(140.dp).clip(RoundedCornerShape(10.dp))
                                                    .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(10.dp)),
                                                    horizontalArrangement = Arrangement.Center,
                                                    verticalAlignment = Alignment.CenterVertically){
                                                    Text(text = MajkoResourceStrings.common_pdf,
                                                        color = Color.White,
                                                        fontWeight = FontWeight.Bold)
                                                }
                                            } })
                                    Spacer(Modifier.width(20.dp))
                                }
                            }
                        }
                        Spacer(Modifier.height(15.dp))

                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                            BlueRoundedButton(onClick = { viewModel.openFile() }, buttonText = "Добавить файл")
                        }
                    }
                }
            }
        }
    }
}



