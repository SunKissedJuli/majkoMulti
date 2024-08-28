package com.example.majkomulti.data.mapper

import com.example.majkomulti.data.models.Task.TaskDataResponse
import com.example.majkomulti.domain.modelsUI.Project.ProjectDataUi
import com.example.majkomulti.domain.modelsUI.Task.TaskDataUi

fun List<TaskDataResponse>.toUI(): List<TaskDataUi> {
    return map { it.toUI() }
}

fun TaskDataResponse.toUI(): TaskDataUi {
    return TaskDataUi(
        id = this.id.orEmpty(),
        createdAt = this.createdAt.orEmpty(),
        updatedAt = this.updatedAt.orEmpty(),
        title = this.title.orEmpty(),
        text = this.text.orEmpty(),
        priority = this.priority ?: 0,
        deadline = this.deadline.orEmpty(),
        status = this.status ?: 0,
        image = this.image.orEmpty(),
        creator = this.creator?.map { it.toUI() } ?: emptyList(),
        mainTaskId = this.mainTaskId.orEmpty(),
        taskMembers = this.taskMembers?.map { it.toUI() } ?: emptyList(),
        isPersonal = this.isPersonal ?: false,
        countSubtasks = this.countSubtasks ?: 0,
        countNotes = this.countNotes ?: 0,
        countFiles = this.countFiles ?: 0,
        isFavorite = this.isFavorite ?: false,
        project = this.project?.toUI()?: ProjectDataUi.empty()
    )
}

