package com.example.majkomulti.screen.taskEditor

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.SubcomposeAsyncImage
import com.example.majkomulti.components.AsyncFormatImageViewer
import com.example.majkomulti.components.BlueRoundedButton
import com.example.majkomulti.components.ButtonBack
import com.example.majkomulti.components.CustomScaffold
import com.example.majkomulti.components.DeadlineDatePicker
import com.example.majkomulti.components.ExitAlertDialog
import com.example.majkomulti.components.HorizontalLine
import com.example.majkomulti.components.SpinnerSample
import com.example.majkomulti.components.TaskCard
import com.example.majkomulti.data.models.NoteData.NoteData
import com.example.majkomulti.data.models.Task.TaskData
import com.example.majkomulti.data.models.Task.TaskUpdateData
import com.example.majkomulti.ext.clickableBlank
import com.example.majkomulti.strings.MajkoResourceStrings
import io.github.skeptick.libres.compose.painterResource
import com.example.majkomulti.images.MajkoResourceImages
import com.example.majkomulti.platform.FilesPicker
import com.example.majkomulti.platform.permission.model.Permission
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class TaskEditorScreen(val taskId: String = "0") : Screen, KoinComponent {
    @Composable
    override fun Content() {
        val colorScheme = MaterialTheme.colorScheme
        val viewModel = rememberScreenModel { TaskEditorViewModel() }
        LaunchedEffect(Unit){
            viewModel.loadData(taskId, colorScheme)
        }
        val uiState by viewModel.stateFlow.collectAsState()
        val navigator = LocalNavigator.currentOrThrow
        val uriHandler = LocalUriHandler.current
        var filePickerShow by remember { mutableStateOf(false) }
        var filePickerPermission by remember { mutableStateOf(false) }
        val scope = rememberCoroutineScope()

        if (uiState.exitDialog) {
            ExitAlertDialog(
                onConfirm = { viewModel.updateExitDialog()
                    viewModel.saveTask(navigator,
                        TaskUpdateData(uiState.taskId, uiState.taskName, uiState.taskText,
                            uiState.taskPriority,uiState.taskDeadline, uiState.taskStatus)
                    ) },
                onDismiss = { viewModel.updateExitDialog()
                    navigator.pop()})
        }

        Column(
            Modifier
                .fillMaxWidth()
                .height(10000.dp)
                .background(uiState.backgroundColor)) {
            //добавление субтаска
            if (uiState.isAdding) {
                AddNewTask(uiState, viewModel, { viewModel.addingTask() })
            }

            CustomScaffold(
                topBar = {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .background(MaterialTheme.colorScheme.primary)
                            .padding(horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        ButtonBack({
                            viewModel.saveTask(
                                navigator, TaskUpdateData(
                                    uiState.taskId, uiState.taskName, uiState.taskText,
                                    uiState.taskPriority, uiState.taskDeadline, uiState.taskStatus
                                )
                            )
                        })

                        Box {
                            IconButton(onClick = { viewModel.updateExpanded() }) {
                                Icon(
                                    painter = painterResource(MajkoResourceImages.icon_menu),
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.background
                                )
                            }
                            DropdownMenu(
                                expanded = uiState.expanded,
                                onDismissRequest = { viewModel.updateExpanded() },
                                modifier = Modifier.fillMaxWidth(0.5f)
                            ) {
                                Row(
                                    Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            viewModel.removeTask(navigator)
                                            viewModel.updateExpanded()
                                        }) {
                                    Text(
                                        MajkoResourceStrings.project_delite,
                                        fontSize = 18.sp,
                                        color = MaterialTheme.colorScheme.onSecondary,
                                        modifier = Modifier.padding(all = 10.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .background(uiState.backgroundColor)) {

                    Column(Modifier.fillMaxWidth()) {
                        BasicTextField(
                            value = uiState.taskName,
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp),
                            textStyle = TextStyle.Default.copy(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                            onValueChange = { viewModel.updateTaskName(it) },
                            decorationBox = { innerTextField ->
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    if (uiState.taskName.isEmpty()) {
                                        Text(text = MajkoResourceStrings.taskeditor_name,
                                            color = MaterialTheme.colorScheme.onSurface, fontSize = 20.sp)
                                    }
                                    innerTextField()
                                }
                            })
                        BasicTextField(
                            value = uiState.taskText,
                            modifier = Modifier.padding(horizontal = 20.dp),
                            textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                            onValueChange = { { viewModel.updateTaskText(it) } },
                            decorationBox = { innerTextField ->
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    if (uiState.taskText.isEmpty()) {
                                        Text(text = MajkoResourceStrings.taskeditor_hint,
                                            color = MaterialTheme.colorScheme.onSurface, fontSize = 18.sp)
                                    }
                                    innerTextField()
                                }
                            })
                    }

                    //отображается только при редактировании, при добавлении таски нельзя добавить субтаску или задачу
                    if (!uiState.taskId.equals("0")) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(max = 10000.dp)
                                .padding(top = 10.dp, bottom = 10.dp, start = 20.dp, end = 20.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(uiState.taskFiles) { file ->
                                AsyncFormatImageViewer(file.link, uriHandler,
                                    modifier = Modifier.height(120.dp).width(120.dp))
                                Spacer(Modifier.width(10.dp))
                            }
                        }

                        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                            BlueRoundedButton(onClick = {
                                scope.launch {
                                    if (viewModel.filePermissionsService.checkPermission(Permission.MANAGE_EXTERNAL_STORAGE)
                                        .granted()) {
                                        filePickerShow = true
                                    } else {
                                        filePickerPermission = true
                                        viewModel.filePermissionsService.providePermission(
                                            Permission.MANAGE_EXTERNAL_STORAGE
                                        )
                                    }
                                }

                            }, buttonText = "Добавить файл")
                            FilesPicker(filePickerShow,
                                fileExtensions = listOf("pdf", "doc", "docx", "pptx", "png", "jpg"))
                            { files ->
                                filePickerPermission = false
                                filePickerShow = false
                                val pathChosen = files ?: return@FilesPicker
                                val newFiles = pathChosen.map { it.path }
                                viewModel.addFile(newFiles, colorScheme)
                            }
                        }
                    }
                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start) {
                        Column(Modifier.padding(start = 20.dp, top = 20.dp, end = 20.dp)) {
                            Row(
                                Modifier
                                    .fillMaxHeight()
                                    .clickable { viewModel.addNewNote() },
                                verticalAlignment = Alignment.CenterVertically) {

                                Icon(painter = painterResource(MajkoResourceImages.icon_plus),
                                    contentDescription = "", tint = MaterialTheme.colorScheme.background)

                                Spacer(modifier = Modifier.width(10.dp))
                                Text(text = MajkoResourceStrings.taskeditor_add,
                                    fontSize = 18.sp, fontWeight = FontWeight.Medium,)
                            }
                            HorizontalLine()
                        }

                        //добавление note
                        if (uiState.newNote) {
                            Column {
                                BasicTextField(
                                    value = uiState.noteText,
                                    modifier = Modifier
                                        .padding(horizontal = 20.dp, vertical = 15.dp),
                                    textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                                    onValueChange = { viewModel.updateNoteText(it) },
                                    decorationBox = { innerTextField ->
                                        Row(modifier = Modifier.fillMaxWidth()) {
                                            if (uiState.noteText.isEmpty()) {
                                                Text(text = MajkoResourceStrings.taskeditor_hint,
                                                    color = MaterialTheme.colorScheme.onSurface, fontSize = 18.sp)
                                            }
                                            innerTextField()
                                        }
                                    })

                                Row(Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center) {

                                    Button(onClick = { viewModel.addNote(NoteData(uiState.taskId, uiState.noteText)) },
                                        shape = CircleShape, modifier = Modifier.fillMaxWidth(0.65f)
                                            .padding(vertical = 10.dp),
                                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)) {
                                        Text(text = MajkoResourceStrings.project_add,
                                            color = MaterialTheme.colorScheme.background,
                                            fontSize = 18.sp, fontWeight = FontWeight.Medium)
                                    }
                                }
                            }
                        }

                        //отображение notes
                        SetNotes(uiState,onUpdateNoteText = viewModel::updateOldNoteText,
                            onSaveNote = viewModel::saveUpdateNote, onRemoveNote = viewModel::removeNote)

                        //отображение субтасков
                        LazyRow(Modifier.padding(all = 5.dp)) {
                            items(uiState.subtask) { subtask ->
                                Column(Modifier.width(200.dp)) {
                                    TaskCard(onClick = { navigator.push(TaskEditorScreen(it)) },
                                        viewModel.getPriority(subtask.priority),
                                        viewModel.getStatusName(subtask.status), subtask)
                                }
                            }
                        }
                    }

                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        BlueRoundedButton({ viewModel.addingTask() }, MajkoResourceStrings.taskeditor_addtask,
                            modifier = Modifier.padding(bottom = 10.dp))
                    }


                    Column(Modifier.fillMaxWidth().padding(start = 15.dp, top = 5.dp, end = 15.dp, bottom = 15.dp)
                        .clip(RoundedCornerShape(20.dp)).background(color = MaterialTheme.colorScheme.background),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Top) {

                        Column(Modifier.padding(horizontal = 20.dp, vertical = 10.dp)) {

                            DeadlineDatePicker(currentDeadline = uiState.taskDeadline,
                                onUpdateDeadline = { newDate -> viewModel.updateTaskDeadlie(newDate) })

                            HorizontalLine()
                            val colorScheme = MaterialTheme.colorScheme
                            if (uiState.taskPriorityName.isNotEmpty() || uiState.taskId == "0") {
                                SpinnerSample(name = MajkoResourceStrings.taskeditor_priority,
                                    items = viewModel.getPriority(),
                                    selectedItem = uiState.taskPriorityName,
                                    { viewModel.updateTaskPriority(it, colorScheme) })
                            }

                            HorizontalLine()
                            Text(
                                text = MajkoResourceStrings.taskeditor_project + " " +
                                        (uiState.taskProjectObj.name.ifEmpty { MajkoResourceStrings.common_no }),
                                fontSize = 18.sp,
                                color = MaterialTheme.colorScheme.onSecondary
                            )
                            HorizontalLine()
                            if (uiState.taskStatusName.isNotEmpty() || uiState.taskId == "0") {
                                SpinnerSample(
                                    name = MajkoResourceStrings.taskeditor_status,
                                    items = viewModel.getStatus(),
                                    selectedItem = uiState.taskStatusName,
                                    { viewModel.updateTaskStatus(it) }
                                )
                            }
                            HorizontalLine()
                        }
                    }
                }
            }
        }
    }
}



@Composable
private fun AddNewTask(uiState: TaskEditorState, viewModel: TaskEditorViewModel, onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(modifier = Modifier.fillMaxWidth().padding(16.dp)
            .clip(RoundedCornerShape(25.dp)).background(MaterialTheme.colorScheme.secondary)) {
            Column {
                Column(Modifier.padding(all = 15.dp).clip(RoundedCornerShape(20.dp))
                    .background(color = MaterialTheme.colorScheme.background),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top) {

                    BasicTextField(
                        value = uiState.subtaskName,
                        modifier = Modifier
                            .padding(start = 18.dp, top = 15.dp, bottom = 5.dp)
                            .fillMaxHeight(0.09f),
                        textStyle = TextStyle.Default.copy(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        onValueChange = { viewModel.updateSubtaskName(it) },
                        maxLines = 2,
                        decorationBox = { innerTextField ->
                            Row(modifier = Modifier.fillMaxWidth()) {
                                if (uiState.subtaskName.isEmpty()) {
                                    Text(text = MajkoResourceStrings.taskeditor_name, color = MaterialTheme.colorScheme.onSurface,
                                        fontSize = 20.sp, maxLines = 2)
                                }
                                innerTextField()
                            }
                        })
                    BasicTextField(
                        value = uiState.subtaskText,
                        modifier = Modifier
                            .fillMaxHeight(0.25f)
                            .padding(horizontal = 18.dp),
                        textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                        onValueChange = { viewModel.updateSubtaskText(it) },
                        decorationBox = { innerTextField ->
                            Row(modifier = Modifier.fillMaxWidth()) {
                                if (uiState.subtaskText.isEmpty()) {
                                    Text(text =MajkoResourceStrings.taskeditor_hint,
                                        color = MaterialTheme.colorScheme.onSurface, fontSize = 18.sp, maxLines = 4)
                                }
                                innerTextField()
                            }
                        }
                    )
                }
                Column(Modifier.fillMaxWidth().padding(start = 15.dp, top = 5.dp, end = 15.dp, bottom = 15.dp)
                    .clip(RoundedCornerShape(20.dp)).background(color = MaterialTheme.colorScheme.background),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top) {

                    Column(Modifier.padding(horizontal = 20.dp, vertical = 10.dp)) {

                        DeadlineDatePicker(currentDeadline = uiState.subtaskDeadline,
                            onUpdateDeadline = { newDate -> viewModel.updateSubtaskDeadlie(newDate) })
                        HorizontalLine()

                        SpinnerSample(name = MajkoResourceStrings.taskeditor_priority,
                            items = viewModel.getPriority(),
                            selectedItem = viewModel.getPriorityName(uiState.subtaskPriority),
                            { viewModel.updateSubtaskPriority(it) })
                        HorizontalLine()
                        Text(text = MajkoResourceStrings.taskeditor_project + " " +
                                (uiState.taskProjectObj.name.ifEmpty { MajkoResourceStrings.common_no }),
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                        HorizontalLine()

                        SpinnerSample(
                            name = MajkoResourceStrings.taskeditor_status,
                            items = viewModel.getStatus(),
                            selectedItem = viewModel.getStatusName(uiState.subtaskStatus),
                            { viewModel.updateSubtaskStatus(it) })
                        HorizontalLine()
                    }
                }

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    horizontalArrangement = Arrangement.Center) {
                    Button(onClick = { viewModel.saveSubtask(
                        TaskData(uiState.subtaskName, uiState.subtaskText,uiState.subtaskDeadline,
                            uiState.subtaskPriority,uiState.subtaskStatus,uiState.taskProject,uiState.taskId)
                    ) },
                        shape = CircleShape,
                        modifier = Modifier
                            .fillMaxWidth(0.65f)
                            .padding(vertical = 10.dp),
                        colors = ButtonDefaults.buttonColors(
                            MaterialTheme.colorScheme.primary)) {
                        Text(text = MajkoResourceStrings.project_add, color = MaterialTheme.colorScheme.background,
                            fontSize = 18.sp, fontWeight = FontWeight.Medium)
                    }
                }
            }
        }
    }
}

@Composable
private fun SetNotes(uiState: TaskEditorState, onUpdateNoteText: (String, String) -> Unit,
                     onSaveNote: (String, String) -> Unit, onRemoveNote: (String) -> Unit){
    Column(Modifier.padding(start = 10.dp, top = 5.dp, end = 10.dp, bottom = 5.dp)){
        for(item in uiState.notes){
            Row(Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {

                Icon(painter =painterResource(MajkoResourceImages.icon_line),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.background)
                Spacer(modifier = Modifier.width(10.dp))

                BasicTextField(
                    value = item.note,
                    textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                    onValueChange = {onUpdateNoteText(it, item.id)},
                    modifier = Modifier.fillMaxWidth(0.7f),
                    decorationBox = { innerTextField ->
                        Row(modifier = Modifier.fillMaxWidth()) {
                            if (item.note.isEmpty()) {
                                Text(text = MajkoResourceStrings.taskeditor_hint,
                                    color = MaterialTheme.colorScheme.surface,fontSize = 18.sp) }
                            innerTextField() } })
                Spacer(modifier = Modifier.width(5.dp))

                IconButton(onClick = { onSaveNote(item.id, item.note) }) {
                    Icon(painter = painterResource(MajkoResourceImages.icon_check),
                        contentDescription = "", tint = MaterialTheme.colorScheme.primary)
                }

                IconButton(onClick = { onRemoveNote(item.id) }) {
                    Icon(painter = painterResource(MajkoResourceImages.icon_delete),
                        contentDescription = "", tint = MaterialTheme.colorScheme.onSecondary)
                }
            }
        }
    }
}
