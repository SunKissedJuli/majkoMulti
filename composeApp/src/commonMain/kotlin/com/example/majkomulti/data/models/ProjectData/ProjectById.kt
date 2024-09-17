package com.example.majkomulti.data.models.ProjectData

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ProjectById(
    @SerialName("projectId") val projectId : String,
)

@Serializable
class ProjectByIdUnderscore (
    @SerialName("project_id") val projectId : String,
)