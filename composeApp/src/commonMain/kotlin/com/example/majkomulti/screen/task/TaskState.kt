package com.example.majkomulti.screen.task

import com.example.majkomulti.domain.modelsUI.InfoUi
import com.example.majkomulti.domain.modelsUI.Task.TaskDataUi

data class TaskState(
    val allTaskList: List<TaskDataUi> = emptyList(),
    val favoritesTaskList: List<TaskDataUi> = emptyList(),
    val personalFavoritesTaskList: List<TaskDataUi> = emptyList(),
    val personalAllTaskList: List<TaskDataUi> = emptyList(),
    val groupAllTaskList: List<TaskDataUi> = emptyList(),
    val groupFavoritesTaskList: List<TaskDataUi> = emptyList(),
    val statuses: List<InfoUi> = emptyList(),
    val priorities: List<InfoUi> = emptyList(),
    val searchString: String = DEFAULT_STRING,
    val isError: Boolean = DEFAULT_BOOLEAN,
    val errorMessage: Int? = null,
    val isMessage: Boolean = DEFAULT_BOOLEAN,
    val message: Int? = null,
    val isLongtap: Boolean = DEFAULT_BOOLEAN,
    val longtapTaskId: String = DEFAULT_STRING,
    val expandedFilter: Boolean = DEFAULT_BOOLEAN,
    val expandedLongTap: Boolean = DEFAULT_BOOLEAN,
    val taskData: TaskDataUi = TaskDataUi.empty(),
    val isTask: Boolean = DEFAULT_BOOLEAN
){
    companion object {
        const val DEFAULT_STRING = ""
        const val DEFAULT_BOOLEAN = false

        fun InitState() = TaskState()
    }
}
