package com.example.majkomulti.domain.modelsUI.Task

import com.example.majkomulti.domain.modelsUI.Project.ProjectDataUi
import com.example.majkomulti.domain.modelsUI.User.CurrentUserDataUi

data class TaskDataUi(
    val id: String,
    val createdAt: String,
    val updatedAt: String,
    val title: String,
    val text: String,
    val priority: Int,
    val deadline: String,
    val status: Int,
    val image: String,
    val creator: List<CurrentUserDataUi>,
    val mainTaskId: String,
    val taskMembers: List<CurrentUserDataUi>,
    val isPersonal: Boolean,
    val countSubtasks: Int,
    val countNotes: Int,
    val countFiles: Int,
    val isFavorite: Boolean,
    val project: ProjectDataUi
)