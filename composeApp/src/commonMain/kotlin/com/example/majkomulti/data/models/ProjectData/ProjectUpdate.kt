package com.example.majkomulti.data.models.ProjectData

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ProjectUpdate(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("description") val description:String,
    @SerialName("is_archive") val isArchive: Int
)