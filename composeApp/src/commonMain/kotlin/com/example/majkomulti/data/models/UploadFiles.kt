package com.example.majkomulti.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.io.File

@Serializable
class UploadFiles(
    @SerialName("file_id") val id: String?,
    @SerialName("link") val link: String?,
    @SerialName("created_at") val createdAt: String?,
)