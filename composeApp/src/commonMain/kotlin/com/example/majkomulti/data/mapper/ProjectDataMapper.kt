package com.example.majkomulti.data.mapper

import com.example.majkomulti.data.models.ProjectData.ProjectDataResponse
import com.example.majkomulti.domain.modelsUI.Project.ProjectDataUi
import com.example.majkomulti.domain.modelsUI.User.CurrentUserDataUi

fun ProjectDataResponse.toUI() : ProjectDataUi {
    return ProjectDataUi(
        id = this.id.orEmpty(),
        createdAt = this.createdAt.orEmpty(),
        updatedAt = this.updatedAt.orEmpty(),
        name = this.name.orEmpty(),
        description = this.description.orEmpty(),
        isArchive = this.isArchive?:0,
        author = this.author?.toUI()?: CurrentUserDataUi.empty(),
        members = this.members?: emptyList(),
        image = this.image.orEmpty(),
        isPersonal = this.isPersonal?:true,
        countFiles = this.countFiles?:0
    )
}
