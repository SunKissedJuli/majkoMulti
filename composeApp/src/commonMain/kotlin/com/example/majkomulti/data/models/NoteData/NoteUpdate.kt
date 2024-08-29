package com.example.majkomulti.data.models.NoteData

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NoteUpdate(
    @SerialName("id") val id: String,
    @SerialName("taskId") val taskId: String,
    @SerialName("text") val text: String
)