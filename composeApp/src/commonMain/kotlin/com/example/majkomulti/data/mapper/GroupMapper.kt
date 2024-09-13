package com.example.majkomulti.data.mapper

import com.example.majkomulti.commons.Constantas
import com.example.majkomulti.data.models.GroupData.GroupMember
import com.example.majkomulti.data.models.GroupData.GroupResponse
import com.example.majkomulti.data.models.NoteData.NoteDataResponse
import com.example.majkomulti.domain.modelsUI.Group.GroupMemberUi
import com.example.majkomulti.domain.modelsUI.Group.GroupUi
import com.example.majkomulti.domain.modelsUI.Note.NoteDataUi
import com.example.majkomulti.domain.modelsUI.Project.ProjectRoleUi
import com.example.majkomulti.domain.modelsUI.User.CurrentUserDataUi

fun List<GroupResponse>.toUI(): List<GroupUi> {
    return map { it.toUI() }
}

fun GroupResponse.toUI(): GroupUi {
    return GroupUi(
        id = this.id.orEmpty(),
        title = this.title.orEmpty(),
        description = this.description.orEmpty(),
        author = this.author?.toUI()?: CurrentUserDataUi.empty(),
        createdAt = this.createdAt.orEmpty(),
        updatedAt = this.updatedAt.orEmpty(),
        parentGroup = this.parentGroup.orEmpty(),
        isPersonal = this.isPersonal?:true,
        image = this.image?.let { Constantas.BASE_URL+this.image } ?: "",
        filesCount = this.filesCount?:0,
        projectsGroup = this.projectsGroup?.map { it.toUI() }?: emptyList(),
        members = this.members?.map { it.toUI() }?: emptyList(),
        files = this.files?.map{it.toUI()} ?: emptyList()
    )
}

fun GroupMember.toUI(): GroupMemberUi {
    return GroupMemberUi(
        id = this.id.orEmpty(),
        user = this.user?.toUI()?: CurrentUserDataUi.empty(),
        role = this.role?.toUI()?: ProjectRoleUi.empty()
    )
}