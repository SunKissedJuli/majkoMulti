package com.example.majkomulti.data.models.GroupData

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroupInviteResponse(
    @SerialName("group_id") val groupId: String?,
    @SerialName("invite") val invite: String?,
    @SerialName("user_id") val userId: String?,
    @SerialName("id") val id: String?,
    @SerialName("updated_at") val updatedAt: String?,
    @SerialName("created_at") val createdAt: String?
)