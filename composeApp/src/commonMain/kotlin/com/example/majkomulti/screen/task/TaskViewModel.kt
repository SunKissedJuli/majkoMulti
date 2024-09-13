package com.example.majkomulti.screen.task

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import com.example.majkomulti.components.SpinnerItems
import com.example.majkomulti.platform.BaseScreenModel
import com.example.majkomulti.data.models.Task.SearchTask
import com.example.majkomulti.data.models.Task.TaskById
import com.example.majkomulti.data.models.Task.TaskByIdUnderscore
import com.example.majkomulti.data.models.Task.TaskUpdateData
import com.example.majkomulti.data.models.UploadFiles
import com.example.majkomulti.domain.manager.AuthManager
import com.example.majkomulti.domain.modelsUI.Task.TaskDataUi
import com.example.majkomulti.domain.repository.InfoRepository
import com.example.majkomulti.domain.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import java.io.File
import javax.swing.JFileChooser

internal class TaskViewModel : BaseScreenModel<TaskState, TaskEvent>(TaskState.InitState) {

    private val taskRepository: TaskRepository by inject()
    private val infoRepository: InfoRepository by inject()
    private val manager: AuthManager by inject()

    fun updateSearchString(newSearchString: String) = blockingIntent {
        reduce {
            state.copy(searchString = newSearchString)
        }
        if(state.isAllTask){
            loadAllTask(newSearchString)
        }else{
            loadSortTasks(newSearchString)
        }

    }

    fun openPanel(id: String)  = blockingIntent {
        val idLength = 36
        val currentIds = state.longtapTaskId.chunked(idLength)
        val updatedIds = if (currentIds.contains(id)) {
            currentIds.filter { it != id }
        } else {
            currentIds + id
        }.joinToString("")
        reduce {
            state.copy(isLongtap = updatedIds.isNotEmpty(), longtapTaskId = updatedIds)
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

    fun getPriority() : List<SpinnerItems>{
        val list = mutableListOf<SpinnerItems>()
        if (state.priorities.isNotEmpty()) {
            for (propriety in state.priorities) {
                list.add(SpinnerItems(propriety.id.toString(), propriety.name))
            }
        }
        return list
    }

    fun getStatusName(statusId: Int): String {
        return if (state.statuses.isNotEmpty()) {
            state.statuses.find { it.id == statusId }?.name ?: ""
        } else {
            ""
        }
    }

    fun getStatus() : List<SpinnerItems>{
        val list = mutableListOf<SpinnerItems>()
        if (state.statuses.isNotEmpty()) {
            for (status in state.statuses) {
                list.add(SpinnerItems(status.id.toString(), status.name))
            }
        }
        return list
    }

    fun getPriorityName(priorityId: Int) : String{
        if(state.priorities.isNotEmpty()){
            for(item in state.priorities){
                if(item.id==priorityId){
                    return item.name
                }
            }
        }
        return ""
    }

    fun getStatus(statusId: Int): String {
        if (state.statuses.isNotEmpty()) {
            for (item in state.statuses) {
                if (item.id == statusId) {
                    return item.name
                }
            }
        }
        return ""
    }

    fun updateExpandedFilter(){

    }

    fun updateTitle(title: String) = blockingIntent {
        reduce { state.copy(taskData = state.taskData.copy(title = title)) }
    }

    fun updateText(text: String) = blockingIntent {
        reduce { state.copy(taskData = state.taskData.copy(text = text)) }
    }

    fun updateExpandedLongTap() = blockingIntent{
        if(state.expandedLongTap){
            reduce { state.copy(expandedLongTap = false) }
        }else{
            reduce { state.copy(expandedLongTap = true) }
        }
    }

    fun filterByStatus(status: Int): List<TaskDataUi> {
        return state.allTaskList?.filter { it.status == status } ?: emptyList()
    }

    fun loadData() = intent{
        reduceLocal { state.copy(isAllTask = true) }
        loadAllTask("")
    }

    fun loadSubData(){
        loadPriorities()
        loadStatuses()
    }

    private fun loadAllTask(search: String) = intent {
        launchOperation(
            operation = {
                taskRepository.getAllUserTask(
                    SearchTask(search)
                )
            },
            success = { response ->
                val fav: MutableList<TaskDataUi> = mutableListOf()
                val notFavorite: MutableList<TaskDataUi> = mutableListOf()
                response.forEach { item ->
                    if (!item.isFavorite) {
                        notFavorite.add(item)
                    }else if(item.isFavorite){
                        fav.add(item)
                    }
                }

                reduceLocal {
                    state.copy( allTaskList = notFavorite.sortedBy { it.status },
                        favoritesTaskList = fav.sortedBy { it.status },
                    )
                }
            },
        )
    }

    fun loadSortData(){
        loadSortTasks("")
    }

    private fun loadSortTasks(search: String) = intent {
        launchOperation(
            operation = {
                taskRepository.getUserTask(
                    SearchTask(search)
                )
            },
            success = { response ->
                val fav: MutableList<TaskDataUi> = mutableListOf()
                val notFavorite: MutableList<TaskDataUi> = mutableListOf()
                response.forEach { item ->
                    if (!item.isFavorite) {
                        notFavorite.add(item)
                    }else if(item.isFavorite){
                        fav.add(item)
                    }
                }

                reduceLocal {
                    state.copy( allTaskList = notFavorite.sortedBy { it.status },
                        favoritesTaskList = fav.sortedBy { it.status },
                    )
                }
            },
        )
    }

    private fun loadStatuses() = intent {
        launchOperation(
            operation = {
                infoRepository.getStatuses()
            },
            success = { response ->
                reduceLocal {
                    state.copy( statuses = response)
                }
            }
        )
    }

    private fun loadPriorities()= intent {
        launchOperation(
            operation = {
                infoRepository.getPriorities()
            },
            success = { response ->
                reduceLocal {
                    state.copy(priorities = response)}
            },
        )
    }

    fun addFavotite(task_id: String) = intent {
        launchOperation(
            operation = {
                taskRepository.addToFavorite(TaskById(task_id))
            },
            success = {
                if(state.isAllTask){ loadData() }
                else{ loadSortData() }
                      },
            loading = { setStatus(false) }
        )
    }

    fun removeFavotite(task_id: String)  = intent {
        launchOperation(
            operation = {
                taskRepository.removeFavotire(TaskById(task_id))
            },
            success = {
                if(state.isAllTask){ loadData() }
                else{ loadSortData() }
            },
            loading = { setStatus(false) }
        )
    }

    fun openDesktopPanel(taskId: String = "") = blockingIntent {
        reduce { state.copy(isTask = !state.isTask) }
        if(state.isTask){
            loadTask(taskId)
        }
        else{
            updateTask()
        }
    }

    fun loadTask(taskId: String) = intent{
        launchOperation(
            operation = {
                taskRepository.getTaskById(TaskById(taskId))
            },
            success = { response ->
                reduceLocal { state.copy(taskData = response) }
            }
        )
    }

    fun updateTask() = intent {
        val task = TaskUpdateData(taskId = state.taskData.id, title = state.taskData.title, text=state.taskData.text,
            priorityId = state.taskData.priority, deadline = state.taskData.deadline, statusId = state.taskData.status)
        launchOperation(
            operation = {
                taskRepository.updateTask(task)
            },
            success = {
                reduceLocal  { state.copy(taskData = TaskDataUi.empty()) }
                loadData()},
            failure = {
                reduceLocal  { state.copy(taskData = TaskDataUi.empty())}
            }
        )
    }

    fun openFile() {
        val fileChooser = JFileChooser()
        val returnValue = fileChooser.showOpenDialog(null)

       if (returnValue == JFileChooser.APPROVE_OPTION) {
           println("!!!!!!!!!!!!!!!!!!!имя выбранного файла: " + fileChooser.selectedFile.name)
          // addFile(fileChooser.selectedFile.toURI().toString())
        }
    }

    fun addFile(file: String) = intent {
       launchOperation(
            operation = {
                infoRepository.uploadFile(taskId = state.taskData.id, files = file)
            },
            success = {loadData()}
        )
    }

    fun removeTask() = intent {
        val projectIds = state.longtapTaskId.chunked(36)
        projectIds.mapNotNull { id ->
            val task = state.allTaskList?.find { it.id == id }
                ?: state.favoritesTaskList?.find { it.id == id }

            task?.let {
                val removeTask = TaskByIdUnderscore(it.id)
                launchOperation(
                    operation = {
                        taskRepository.removeTask(removeTask)
                    },
                    success = { loadData() }
                )
            }
        }
    }

    fun updateTaskPriority(prioryti: String) {
        blockingIntent {
            reduce { state.copy(taskData = state.taskData.copy(priority = prioryti.toInt())) }
        }
    }

    fun updateTaskStatus(status:String) = blockingIntent {
        reduce { state.copy(taskData = state.taskData.copy(status = status.toInt())) }
    }

    fun updateStatus() {

    }
}
