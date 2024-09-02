package com.example.majkomulti.screen.task

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.majkomulti.platform.BaseScreenModel
import com.example.majkomulti.data.models.Task.SearchTask
import com.example.majkomulti.data.models.Task.TaskByIdUnderscore
import com.example.majkomulti.domain.modelsUI.Task.TaskDataUi
import com.example.majkomulti.domain.repository.InfoRepository
import com.example.majkomulti.domain.repository.TaskRepository
import org.koin.core.component.inject
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

internal class TaskViewModel : BaseScreenModel<TaskState, Unit>(TaskState.InitState()) {

    private val taskRepository: TaskRepository by inject()
    private val infoRepository: InfoRepository by inject()

    fun updateSearchString(newSearchString: String, whatFilter: Int) = blockingIntent {
        reduce {
            state.copy(searchString = newSearchString)
        }
        loadAllTask(newSearchString)
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

    fun getStatus(statusId: Int): String {
        if (!state.statuses.isNullOrEmpty()) {
            for (item in state.statuses!!) {
                if (item.id == statusId) {
                    return item.name
                }
            }
        }
        return ""
    }

    fun updateExpandedFilter(){

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

    fun filterByStatusPersonal(status: Int): List<TaskDataUi> {
        return state.personalAllTaskList?.filter { it.status == status } ?: emptyList()
    }

    fun filterByStatusGroup(status: Int): List<TaskDataUi> {
        return state.groupAllTaskList?.filter { it.status == status } ?: emptyList()
    }

    fun loadData() {
        loadAllTask("")
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
                val favPersonal: MutableList<TaskDataUi> = mutableListOf()
                val notFavoritePersonal: MutableList<TaskDataUi> = mutableListOf()
                val favGroup: MutableList<TaskDataUi> = mutableListOf()
                val notFavoriteGroup: MutableList<TaskDataUi> = mutableListOf()
                response.forEach { item ->
                    if (!item.isFavorite) {
                        notFavorite.add(item)
                    }else if(item.isFavorite){
                        fav.add(item)
                    }
                }
                response.forEach { item ->
                    if(item.isPersonal){
                        if (!item.isFavorite) {
                            notFavoritePersonal.add(item)
                        }else if(item.isFavorite){
                            favPersonal.add(item)
                        }
                    }
                }
                response.forEach { item ->
                    if(!item.isPersonal){
                        if (!item.isFavorite) {
                            notFavoriteGroup.add(item)
                        }else if(item.isFavorite){
                            favGroup.add(item)
                        }
                    }
                }

                reduceLocal {
                    state.copy( allTaskList = notFavorite.sortedBy { it.status },
                        favoritesTaskList = fav.sortedBy { it.status },
                        personalAllTaskList = notFavoritePersonal.sortedBy { it.status },
                        personalFavoritesTaskList = favPersonal.sortedBy { it.status },
                        groupAllTaskList = notFavoriteGroup.sortedBy { it.status },
                        groupFavoritesTaskList = favGroup.sortedBy { it.status })
                }
            }
        )
    }

    private fun loadFavTask(search: String) {

    }

    private fun loadEachTask(search: String) {

    }

    private fun loadStatuses()= intent {
        launchOperation(
            operation = {
                infoRepository.getStatuses()
            },
            success = { response ->
                reduceLocal {
                    state.copy( statuses = response,)
                }
            },
            failure = {
            }
        )
    }

    fun addFavotite(task_id: String) {

    }

    fun removeFavotite(task_id: String) {

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

    fun updateStatus() {

    }
}
