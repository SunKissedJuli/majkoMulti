package com.example.majkomulti.data.models.GroupData

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroupUpdate(
    @SerialName("groupId") val groupId: String,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
)