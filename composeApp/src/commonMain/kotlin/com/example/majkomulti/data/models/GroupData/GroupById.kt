package com.example.majkomulti.data.models.GroupData

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class GroupById(
    @SerialName("groupId") val groupId: String
)
@Serializable
class GroupByIdUnderscore(
    @SerialName("group_id") val groupId: String
)