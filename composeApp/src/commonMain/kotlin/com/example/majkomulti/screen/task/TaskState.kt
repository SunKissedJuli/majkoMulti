package com.example.majkomulti.screen.task

import com.example.majkomulti.commons.Constantas.DEFAULT_BOOLEAN
import com.example.majkomulti.commons.Constantas.DEFAULT_STRING
import com.example.majkomulti.domain.modelsUI.InfoUi
import com.example.majkomulti.domain.modelsUI.Task.TaskDataUi

data class TaskState(
    val allTaskList: List<TaskDataUi>,
    val favoritesTaskList: List<TaskDataUi>,

    val statuses: List<InfoUi>,
    val priorities: List<InfoUi>,
    val searchString: String,
    val isAllTask: Boolean,
    val isError: Boolean,
    val errorMessage: Int?,
    val isMessage: Boolean,
    val message: Int?,
    val isLongtap: Boolean,
    val longtapTaskId: String,
    val expandedFilter: Boolean,
    val expandedLongTap: Boolean,
    val taskData: TaskDataUi,
    val isTask: Boolean
) {
    companion object {
        val InitState = TaskState(
            allTaskList = emptyList(),
            favoritesTaskList = emptyList(),
            isAllTask = DEFAULT_BOOLEAN,
            statuses = emptyList(),
            priorities = emptyList(),
            searchString = DEFAULT_STRING,
            isError = DEFAULT_BOOLEAN,
            errorMessage = null,
            isMessage = DEFAULT_BOOLEAN,
            message = null,
            isLongtap = DEFAULT_BOOLEAN,
            longtapTaskId = DEFAULT_STRING,
            expandedFilter = DEFAULT_BOOLEAN,
            expandedLongTap = DEFAULT_BOOLEAN,
            taskData = TaskDataUi.empty(),
            isTask = DEFAULT_BOOLEAN
        )
    }
}