package com.example.majkomulti.screen.task

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.majkomulti.platform.BaseScreenModel
import com.example.majkomulti.data.models.Task.SearchTask
import com.example.majkomulti.domain.modelsUI.Task.TaskDataUi
import com.example.majkomulti.domain.repository.InfoRepository
import com.example.majkomulti.domain.repository.TaskRepository
import org.koin.core.component.inject
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

internal class TaskViewModel : BaseScreenModel<TaskState, Unit>(TaskState.InitState()) {

    val taskRepository: TaskRepository by inject()
    val infoRepository: InfoRepository by inject()

    fun updateSearchString(newSearchString: String, whatFilter: Int) = blockingIntent {
        reduce {
            state.copy(searchString = newSearchString)
        }
    }

    fun openPanel(id: String) {

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

    fun updateExpandedLongTap(){

    }

    fun filterByStatus(status: Int): List<TaskDataUi> {
        return state.searchAllTaskList?.filter { it.status == status } ?: emptyList()
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
                response.forEach { item ->
                    if (!item.isFavorite) {
                        notFavorite.add(item)
                    }else if(item.isFavorite){
                        fav.add(item)
                    }
                }
                reduceLocal {
                    state.copy( allTaskList = notFavorite.sortedBy { it.status },
                        searchAllTaskList = notFavorite.sortedBy { it.status },
                        favoritesTaskList = fav.sortedBy { it.status },
                        searchFavoritesTaskList = fav.sortedBy { it.status })
                }
            },
            failure = {
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

    fun removeTask() {
    }

    fun updateStatus() {

    }
}
