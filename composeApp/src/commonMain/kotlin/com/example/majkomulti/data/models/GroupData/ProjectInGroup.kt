package com.example.majkomulti.data.models.GroupData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ProjectInGroup(
    @SerialName("project_id") val projectId: String,
    @SerialName("group_id") val groupId: String
)