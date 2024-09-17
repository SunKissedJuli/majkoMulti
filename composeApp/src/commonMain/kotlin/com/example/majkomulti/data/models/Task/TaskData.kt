package com.example.majkomulti.data.models.Task
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class TaskData(
    @SerialName("title") val title : String?,
    @SerialName("text") val text : String?,
    @SerialName("deadline") val deadline : String?,
    @SerialName("priority_id") val priorityId : Int?,
    @SerialName("status_id") val statusId : Int?,
    @SerialName("project_id") val projectId : String?,
    @SerialName("mainTaskId") val mainTaskId : String?,
)