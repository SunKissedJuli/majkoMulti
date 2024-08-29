package com.example.majkomulti.data.mapper

import com.example.majkomulti.data.models.ProjectData.ProjectCreateInviteResponse
import com.example.majkomulti.domain.modelsUI.Project.ProjectCreateInviteUi

fun ProjectCreateInviteResponse.toUI(): ProjectCreateInviteUi {
    return ProjectCreateInviteUi(
        projectId = this.projectId.orEmpty(),
        invite = this.invite.orEmpty(),
        userId = this.userId.orEmpty(),
        id = this.id.orEmpty(),
        updatedAt = this.updatedAt.orEmpty(),
        createdAt = this.createdAt.orEmpty()
    )
}