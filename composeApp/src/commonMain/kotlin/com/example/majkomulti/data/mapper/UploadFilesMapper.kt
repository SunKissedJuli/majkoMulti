package com.example.majkomulti.data.mapper

import com.example.majkomulti.data.models.Task.TaskDataResponse
import com.example.majkomulti.data.models.UploadFiles
import com.example.majkomulti.domain.modelsUI.Task.TaskDataUi
import com.example.majkomulti.domain.modelsUI.UploadFilesUi

fun List<UploadFiles>.toUI(): List<UploadFilesUi> {
    return map { it.toUI() }
}

fun UploadFiles.toUI(): UploadFilesUi {
    return UploadFilesUi(
        id = this.id.orEmpty(),
        link = this.link.orEmpty(),
        createdAt = this.createdAt.orEmpty()
    )
}