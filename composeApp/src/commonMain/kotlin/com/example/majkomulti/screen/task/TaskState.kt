package com.example.majkomulti.screen.task

import com.example.majkomulti.domain.modelsUI.InfoUi
import com.example.majkomulti.domain.modelsUI.Task.TaskDataUi

data class TaskState(
    val allTaskList: List<TaskDataUi>? = listOf(),
    val favoritesTaskList: List<TaskDataUi>? = listOf(),
    val personalFavoritesTaskList: List<TaskDataUi>? = listOf(),
    val personalAllTaskList: List<TaskDataUi>? = listOf(),
    val groupAllTaskList: List<TaskDataUi>? = listOf(),
    val groupFavoritesTaskList: List<TaskDataUi>? = listOf(),
    val statuses: List<InfoUi> = listOf(),
    val searchString: String = DEFAULT_STRING,
    val isError: Boolean = DEFAULT_BOOLEAN,
    val errorMessage: Int? = null,
    val isMessage: Boolean = DEFAULT_BOOLEAN,
    val message: Int? = null,
    val isLongtap: Boolean = DEFAULT_BOOLEAN,
    val longtapTaskId: String = DEFAULT_STRING,
    val expandedFilter: Boolean = DEFAULT_BOOLEAN,
    val expandedLongTap: Boolean = DEFAULT_BOOLEAN,
){
    companion object {
        const val DEFAULT_STRING = ""
        const val DEFAULT_BOOLEAN = false

        fun InitState() = TaskState()
    }
}
