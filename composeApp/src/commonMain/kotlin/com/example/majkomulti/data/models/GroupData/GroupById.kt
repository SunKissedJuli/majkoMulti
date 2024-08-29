package com.example.majkomulti.data.models.GroupData

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroupById(
    @SerialName("groupId") val groupId: String
)
@Serializable
data class GroupByIdUnderscore(
    @SerialName("group_id") val groupId: String
)