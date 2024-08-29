package com.example.majkomulti.data.models.ProjectData

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProjectCreateInviteResponse(
    @SerialName("project_id") val projectId: String?,
    @SerialName("invite") val invite: String?,
    @SerialName("user_id") val userId: String?,
    @SerialName("id") val id: String?,
    @SerialName("updated_at") val updatedAt: String?,
    @SerialName("created_at") val createdAt: String?,
)
