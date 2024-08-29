package com.example.majkomulti.domain.modelsUI.Note

import com.example.majkomulti.domain.modelsUI.User.CurrentUserDataUi

data class NoteDataUi(
    val id: String,
    val note: String,
    val author: CurrentUserDataUi,
    val createdAt: String,
    val updatedAt: String,
    val isPersonal: Boolean,
    val files: List<String>,
    val countFiles: Int
)