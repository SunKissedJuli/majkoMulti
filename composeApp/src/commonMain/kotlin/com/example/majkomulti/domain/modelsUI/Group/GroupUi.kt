package com.example.majkomulti.domain.modelsUI.Group

import com.example.majkomulti.domain.modelsUI.Project.ProjectDataUi
import com.example.majkomulti.domain.modelsUI.Project.ProjectRoleUi
import com.example.majkomulti.domain.modelsUI.User.CurrentUserDataUi

data class GroupUi(
    val id: String,
    val title: String,
    val description: String,
    val author: CurrentUserDataUi,
    val createdAt: String,
    val updatedAt: String,
    val parentGroup: String,
    val isPersonal: Boolean,
    val image: String?,
    val filesCount: Int,
    val projectsGroup: List<ProjectDataUi>,
    val members: List<GroupMemberUi>,
    val files: List<Any>
){
    companion object {
        fun empty() = GroupUi(
            id = "",
            title = "",
            description = "",
            author = CurrentUserDataUi.empty(),
            createdAt = "",
            updatedAt = "",
            parentGroup = "",
            isPersonal = false,
            image = null,
            filesCount = 0,
            projectsGroup = emptyList(),
            members = emptyList(),
            files = emptyList()
        )
    }
}

data class GroupMemberUi(
    val id: String,
    val user: CurrentUserDataUi,
    val role: ProjectRoleUi
)


