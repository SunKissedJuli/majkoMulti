package com.example.majkomulti.data.mapper

import com.example.majkomulti.data.models.Info
import com.example.majkomulti.data.models.Task.TaskDataResponse
import com.example.majkomulti.domain.modelsUI.InfoUi
import com.example.majkomulti.domain.modelsUI.Task.TaskDataUi

fun List<Info>.toUI(): List<InfoUi> {
    return map { it.toUI() }
}

fun Info.toUI() : InfoUi {
    return InfoUi(
        id = this.id?: 0,
        name = this.name.orEmpty(),
        createdAt = this.createdAt.orEmpty(),
        updatedAt = this.updatedAt.orEmpty()
    )
}