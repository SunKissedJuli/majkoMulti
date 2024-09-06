package com.example.majkomulti.domain.modelsUI.Task

import com.example.majkomulti.data.models.UploadFiles
import com.example.majkomulti.domain.modelsUI.Project.ProjectCurrentUi
import com.example.majkomulti.domain.modelsUI.Project.ProjectDataUi
import com.example.majkomulti.domain.modelsUI.UploadFilesUi
import com.example.majkomulti.domain.modelsUI.User.CurrentUserDataUi

data class TaskDataUi(
    var id: String,
    var createdAt: String,
    var updatedAt: String,
    var title: String,
    var text: String,
    var priority: Int,
    var deadline: String,
    var status: Int,
    var image: String?,
    var creator: List<CurrentUserDataUi>,
    var mainTaskId: String,
    var taskMembers: List<CurrentUserDataUi>,
    var isPersonal: Boolean,
    var countSubtasks: Int,
    var countNotes: Int,
    var countFiles: Int,
    var isFavorite: Boolean,
    var project: ProjectDataUi,
    var files: List<UploadFilesUi>
){
    companion object {
        fun empty() = TaskDataUi(
            id = "",
            createdAt = "",
            updatedAt = "",
            text = "",
            title = "",
            deadline = "",
            creator = listOf(),
            taskMembers = emptyList(),
            image = null,
            isPersonal = false,
            countFiles = 0,
            isFavorite = false,
            countNotes = 0,
            mainTaskId = "",
            priority = 0,
            status = 0,
            countSubtasks = 0,
            project = ProjectDataUi.empty(),
            files = emptyList()
        )
    }
}