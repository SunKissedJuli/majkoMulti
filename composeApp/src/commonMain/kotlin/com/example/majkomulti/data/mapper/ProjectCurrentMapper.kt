package com.example.majkomulti.data.mapper

import com.example.majkomulti.data.models.ProjectData.File
import com.example.majkomulti.data.models.ProjectData.Group
import com.example.majkomulti.data.models.ProjectData.Member
import com.example.majkomulti.data.models.ProjectData.ProjectCurrentResponse
import com.example.majkomulti.data.models.ProjectData.ProjectRole
import com.example.majkomulti.domain.modelsUI.Project.FileUi
import com.example.majkomulti.domain.modelsUI.Project.GroupUi
import com.example.majkomulti.domain.modelsUI.Project.MemberUi
import com.example.majkomulti.domain.modelsUI.Project.ProjectCurrentUi
import com.example.majkomulti.domain.modelsUI.Project.ProjectRoleUi
import com.example.majkomulti.domain.modelsUI.User.CurrentUserDataUi

fun ProjectCurrentResponse.toUI(): ProjectCurrentUi {
    return ProjectCurrentUi(
        id = this.id.orEmpty(),
        createdAt = this.createdAt.orEmpty(),
        updatedAt = this.updatedAt.orEmpty(),
        name = this.name.orEmpty(),
        description = this.description.orEmpty(),
        isArchive = this.isArchive ?: 0,
        author = this.author?.toUI() ?: CurrentUserDataUi.empty(),
        members = this.members?.map { it.toUI() } ?: emptyList(),
        image = this.image,
        isPersonal = this.isPersonal ?: false,
        countFiles = this.countFiles ?: 0,
        tasks = this.tasks?.map { it.toUI() } ?: emptyList(),
       // groups = this.groups?.map { it.toUI() } ?: emptyList(),
        groups = emptyList(),
        files = this.files?.map { it.toUI() } ?: emptyList()
    )
}

fun Group.toUI(): GroupUi {
    return GroupUi(
        id = this.id.orEmpty(),
        name = this.name.orEmpty()
    )
}

fun File.toUI(): FileUi {
    return FileUi(
        id = this.id.orEmpty(),
        name = this.name.orEmpty(),
        url = this.url.orEmpty()
    )
}

fun Member.toUI(): MemberUi {
    return MemberUi(
        projectMemberId = this.projectMemberId.orEmpty(),
        user = this.user?.toUI() ?: CurrentUserDataUi.empty(),
        roleId = this.roleId?.toUI() ?: ProjectRoleUi.empty()
    )
}

fun ProjectRole.toUI(): ProjectRoleUi {
    return ProjectRoleUi(
        id = this.id?:0,
        name = this.name.orEmpty(),
        createdAt = this.createdAt.orEmpty(),
        updatedAt = this.updatedAt.orEmpty(),
    )
}