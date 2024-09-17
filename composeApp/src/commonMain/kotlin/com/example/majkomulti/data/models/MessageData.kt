package com.example.majkomulti.data.models
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class MessageData(
    @SerialName("message") val message : String?
)