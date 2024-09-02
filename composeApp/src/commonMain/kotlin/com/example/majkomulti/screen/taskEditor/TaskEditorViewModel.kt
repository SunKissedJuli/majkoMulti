package com.example.majkomulti.screen.taskEditor

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.navigator.Navigator
import com.example.majkomulti.components.SpinnerItems
import com.example.majkomulti.data.models.NoteData.NoteById
import com.example.majkomulti.data.models.NoteData.NoteData
import com.example.majkomulti.data.models.NoteData.NoteUpdate
import com.example.majkomulti.data.models.Task.TaskById
import com.example.majkomulti.data.models.Task.TaskByIdUnderscore
import com.example.majkomulti.data.models.Task.TaskData
import com.example.majkomulti.data.models.Task.TaskUpdateData
import com.example.majkomulti.domain.repository.InfoRepository
import com.example.majkomulti.domain.repository.TaskRepository
import com.example.majkomulti.platform.BaseScreenModel
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

internal class TaskEditorViewModel: BaseScreenModel<TaskEditorState,Unit>(TaskEditorState.InitState()){

    private val taskRepository: TaskRepository by inject()
    private val infoRepository: InfoRepository by inject()

    fun updateTaskText(text: String) = blockingIntent {
        reduce { state.copy(taskText = text) }
    }

    fun updateTaskName(name:String)= blockingIntent {
        reduce { state.copy(taskName = name) }
    }

    fun updateTaskPriority(prioryti: String, colorScheme: ColorScheme) {
        val background = when (prioryti.toInt()) {
            1 -> colorScheme.onTertiary
            2 -> colorScheme.tertiaryContainer
            3 -> colorScheme.onTertiaryContainer
            4 -> colorScheme.tertiary
            else -> colorScheme.background
        }

        blockingIntent {
            reduce { state.copy(taskPriority = prioryti.toInt(), backgroundColor = background) }
        }
    }

    fun updateTaskStatus(status:String) = blockingIntent {
        reduce { state.copy(taskStatus = status.toInt()) }
    }

    fun updateNoteText(note:String)= blockingIntent {
        reduce { state.copy(noteText = note) }
    }

    fun updateExpanded() = blockingIntent{
        if(state.expanded){
            reduce { state.copy(expanded = false) }
        }else{
            reduce { state.copy(expanded = true) }
        }
    }

    fun updateExitDialog()= blockingIntent{
        if(state.exitDialog){
            reduce { state.copy(exitDialog = false) }
        }else{
            reduce { state.copy(exitDialog = true) }
        }
    }

    fun updateSubtaskText(text: String) = blockingIntent {
        reduce { state.copy(subtaskText = text) }
    }

    fun updateSubtaskName(name:String)= blockingIntent {
        reduce { state.copy(subtaskName = name) }
    }

    fun updateSubtaskStatus(status:String)= blockingIntent {
        reduce { state.copy(subtaskStatus = status.toInt()) }
    }

    fun updateSubtaskDeadlie(deadline:String)= blockingIntent {
        reduce { state.copy(subtaskDeadline = deadline) }
    }

    fun updateSubtaskPriority(prioryti:String)= blockingIntent {
        reduce { state.copy(subtaskPriority = prioryti.toInt()) }
    }

    fun updateOldNoteText(noteText:String, id: String) = blockingIntent {

    }

    fun addingTask() = blockingIntent{
        if(state.isAdding){
            reduce {  state.copy(isAdding = false) }
        }else{
            reduce {  state.copy(isAdding = true) }
        }

    }

    fun addNewNote() = blockingIntent {
        if(!state.newNote){
            reduce {  state.copy(newNote = true) }
        }else{
            reduce {  state.copy(newNote = false, noteText = "") }
        }

    }

    fun updateTaskDeadlie(deadline:String) = blockingIntent {
        reduce {  state.copy(taskDeadline = deadline) }
    }

    fun getStatus() : List<SpinnerItems>{
        val list = mutableListOf<SpinnerItems>()
        if (!state.statuses.isNullOrEmpty()) {
            for (status in state.statuses!!) {
                list.add(SpinnerItems(status.id.toString(), status.name))
            }
        }
        return list
    }

    fun getPriority() : List<SpinnerItems>{
        val list = mutableListOf<SpinnerItems>()
        if (!state.proprieties.isNullOrEmpty()) {
            for (propriety in state.proprieties!!) {
                list.add(SpinnerItems(propriety.id.toString(), propriety.name))
            }
        }
        return list
    }

    fun getStatusName(statusId: Int): String {
        return if (!state.statuses.isNullOrEmpty()) {
            state.statuses.find { it.id == statusId }?.name ?: ""
        } else {
            ""
        }
    }

    @Composable
    fun getPriority(priorityId: Int): Color {
        return when (priorityId) {
            1 -> MaterialTheme.colorScheme.onTertiary
            2 -> MaterialTheme.colorScheme.tertiaryContainer
            3 -> MaterialTheme.colorScheme.onTertiaryContainer
            4 -> MaterialTheme.colorScheme.tertiary
            else -> MaterialTheme.colorScheme.background
        }
    }

    fun getPriorityName(priorityId: Int) : String{
        if(!state.proprieties.isNullOrEmpty()){
            for(item in state.proprieties!!){
                if(item.id==priorityId){
                    return item.name
                }
            }
        }
        return ""
    }

    fun saveUpdateNote(noteId: String, noteText: String) = intent {
        if(!state.taskId.equals("0")){
            launchOperation(
                operation = {
                    taskRepository.updateNote(NoteUpdate(noteId,state.taskId, noteText))
                },
                success = {
                    loadNotesData()
                }
            )
        }
    }

    fun removeNote(noteId: String) = intent {
        if(!state.taskId.equals("0")){
            launchOperation(
                operation = {
                    taskRepository.removeNote(NoteById(noteId))
                },
                success = {
                    loadNotesData()
                }
            )
        }
    }

    fun saveTask(navigator: Navigator, newTask: TaskUpdateData) = intent{
        if(!state.taskId.equals("0")){
            updateTask(navigator, newTask)
        }else{
            navigator.pop()
            launchOperation(
                operation = {
                    val newUserTask = TaskData(state.taskName, state.taskText,state.taskDeadline,
                        state.taskPriority,state.taskStatus,state.taskProject,"")
                    taskRepository.postNewTask(newUserTask)
                }
            )
        }
    }

    fun updateTask(navigator: Navigator, newTask: TaskUpdateData)= intent {
        navigator.pop()
        launchOperation(
            operation = {
                taskRepository.updateTask(newTask)
            }
        )
    }

    fun saveSubtask(newTask: TaskData) = intent {
        if(!state.taskId.equals("0")){
            launchOperation(
                operation = {
                    taskRepository.postNewTask(newTask)
                },
                success = {
                    addingTask()
                    loadSubtaskData()
                }
            )
        }
    }

    fun removeTask(navigator: Navigator) = intent {
        navigator.pop()
        if(!state.taskId.equals("0")){
            launchOperation(
                operation = {
                    taskRepository.removeTask(TaskByIdUnderscore(state.taskId))
                },
            )
        }
    }

    fun loadData(taskId: String, colorScheme: ColorScheme) = intent {
        reduce { state.copy(taskId = taskId) }
        if(!taskId.equals("0")){
            launchOperation(
                operation = {
                    taskRepository.getTaskById(TaskById(state.taskId))
                },
                success = { response ->
                    if(response.countNotes!=0){ loadNotesData() }
                    if(response.countSubtasks!=0){ loadSubtaskData() }

                    reduceLocal { state.copy(
                        taskId = taskId,
                        taskDeadline = response.deadline,
                        taskName = response.title,
                        taskText = response.text,
                        taskStatus = response.status,
                        taskPriority = response.priority,
                        taskProjectObj = response.project,
                        taskProject = response.project.id
                    ) }

                    updateTaskPriority(response.priority.toString(), colorScheme)
                    loadStatuses()
                    loadPriorities()
                }
            )
        }
        else{
            loadStatuses()
            loadPriorities()
        }
    }


    private fun loadStatuses() = intent {
        launchOperation(
            operation = {
                infoRepository.getStatuses()
            },
            success = { response ->
                reduceLocal { state.copy(statuses = response,
                    taskStatusName = getStatusName(state.taskStatus)) }
            }
        )
    }

    private fun loadPriorities()= intent {
        launchOperation(
            operation = {
                infoRepository.getPriorities()
            },
            success = { response ->
                reduceLocal { state.copy(proprieties = response,
                    taskPriorityName = getPriorityName(state.taskPriority)) }
            }
        )
    }

    fun addNote(note: NoteData)= intent {
        launchOperation(
            operation = {
                taskRepository.addNote(note)
            },
            success = {
                addNewNote()
                loadNotesData()
            }
        )
    }

    private fun loadSubtaskData() = intent {
        if(!state.taskId.equals("0")){
            launchOperation(
                operation = {
                    taskRepository.getSubtask(TaskById(state.taskId))
                },
                success = { response ->
                    reduceLocal { state.copy(subtask = response) }
                }
            )
        }
    }

    private fun loadNotesData() = intent {
        launchOperation(
            operation = {
                taskRepository.getNotes(TaskById(state.taskId))
            },
            success = { response ->
                reduceLocal { state.copy(notes = response) }
            }
        )
    }
}