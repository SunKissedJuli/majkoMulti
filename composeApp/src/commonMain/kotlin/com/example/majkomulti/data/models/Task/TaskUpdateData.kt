package com.example.majkomulti.data.models.Task
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaskUpdateData(
    @SerialName("taskId") val taskId : String,
    @SerialName("title") val title : String,
    @SerialName("text") val text : String,
    @SerialName("priority_id") val priorityId : Int,
    @SerialName("deadline") val deadline : String,
    @SerialName("status_id") val statusId : Int,
)