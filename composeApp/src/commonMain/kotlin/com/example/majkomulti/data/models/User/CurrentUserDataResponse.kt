package com.example.majkomulti.data.models.User

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CurrentUserDataResponse(
    @SerialName("id") val id : String?,
    @SerialName("createdAt") val createdAt : String?,
    @SerialName("updatedAt") val updatedAt : String?,
    @SerialName("name") val name : String?,
    @SerialName("image") val image : String?,
    @SerialName("email") val email : String?,
)