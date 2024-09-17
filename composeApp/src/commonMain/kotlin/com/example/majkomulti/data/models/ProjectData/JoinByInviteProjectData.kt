package com.example.majkomulti.data.models.ProjectData

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class JoinByInviteProjectData(
    @SerialName("invite") val invite:String
)