package com.example.majkomulti.domain.modelsUI.Project

import com.example.majkomulti.domain.modelsUI.Group.GroupUi
import com.example.majkomulti.domain.modelsUI.Task.TaskDataUi
import com.example.majkomulti.domain.modelsUI.User.CurrentUserDataUi

data class ProjectCurrentUi(
    val id: String,
    val createdAt: String,
    val updatedAt: String,
    val name: String,
    val description: String,
    val isArchive: Int,
    val author: CurrentUserDataUi,
    val members: List<MemberUi>,
    val image: String?,
    val isPersonal: Boolean,
    val countFiles: Int,
    val tasks: List<TaskDataUi>,
    val groups: List<GroupUi>,
    val files: List<FileUi>
){
    companion object {
        fun empty() = ProjectCurrentUi(
            id = "",
            createdAt = "",
            updatedAt = "",
            name = "",
            description = "",
            isArchive = 0,
            author = CurrentUserDataUi.empty(),
            members = emptyList(),
            image = null,
            isPersonal = false,
            countFiles = 0,
            tasks = emptyList(),
            groups = emptyList(),
            files = emptyList()
        )
    }
}

data class GroupUi(
    val id: String,
    val name: String
)

data class FileUi(
    val id: String,
    val name: String,
    val url: String
)

data class MemberUi(
    val projectMemberId: String,
    val user: CurrentUserDataUi,
    val roleId: ProjectRoleUi
)

data class ProjectRoleUi(
    val id: Int,
    val name: String,
    val createdAt: String,
    val updatedAt: String,
){
    companion object{
        fun empty() = ProjectRoleUi(
            id = 0,
            name = "",
            createdAt = "",
            updatedAt = ""
        )
    }
}
