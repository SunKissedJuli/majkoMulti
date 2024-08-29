package com.example.majkomulti.data.mapper

import com.example.majkomulti.data.models.NoteData.NoteDataResponse
import com.example.majkomulti.data.models.Task.TaskDataResponse
import com.example.majkomulti.domain.modelsUI.Note.NoteDataUi
import com.example.majkomulti.domain.modelsUI.Task.TaskDataUi
import com.example.majkomulti.domain.modelsUI.User.CurrentUserDataUi
fun List<NoteDataResponse>.toUI(): List<NoteDataUi> {
    return map { it.toUI() }
}

fun NoteDataResponse.toUI(): NoteDataUi {
    return NoteDataUi(
        id = this.id.orEmpty(),
        note = this.note.orEmpty(),
        author = this.author?.toUI()?: CurrentUserDataUi.empty(),
        createdAt = this.createdAt.orEmpty(),
        updatedAt = this.updatedAt.orEmpty(),
        isPersonal = this.isPersonal?:true,
        files = this.files?: emptyList(),
        countFiles = this.countFiles?:0
    )
}