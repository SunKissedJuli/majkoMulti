package com.example.majkomulti.data.models.NoteData
import com.example.majkomulti.data.models.User.CurrentUserDataResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class NoteDataResponse(
    @SerialName("id") val id: String?,
    @SerialName("note") val note: String?,
    @SerialName("author") val author: CurrentUserDataResponse?,
    @SerialName("createdAt") val createdAt: String?,
    @SerialName("updatedAt") val updatedAt: String?,
    @SerialName("isPersonal") val isPersonal: Boolean?,
    @SerialName("files") val files: List<String>?,
    @SerialName("countFiles") val countFiles: Int?
)