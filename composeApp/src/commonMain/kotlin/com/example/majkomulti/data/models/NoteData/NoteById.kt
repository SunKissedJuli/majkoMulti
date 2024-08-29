package com.example.majkomulti.data.models.NoteData

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NoteById(
    @SerialName("note_id") val noteId: String
)