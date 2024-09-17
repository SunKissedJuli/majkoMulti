package com.example.majkomulti.data.models.ProjectData

import com.example.majkomulti.data.models.User.CurrentUserDataResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ProjectDataResponse(
    @SerialName("id") val id: String?,
    @SerialName("createdAt") val createdAt: String?,
    @SerialName("updatedAt") val updatedAt: String?,
    @SerialName("name") val name: String?,
    @SerialName("description") val description: String?,
    @SerialName("is_archive") var isArchive: Int?,
    @SerialName("author") val author: CurrentUserDataResponse?,
    @SerialName("members") val members: List<Member>?,
    @SerialName("image") val image: String?,
    @SerialName("is_personal") var isPersonal: Boolean?,
    @SerialName("countFiles") val countFiles: Int?,
)