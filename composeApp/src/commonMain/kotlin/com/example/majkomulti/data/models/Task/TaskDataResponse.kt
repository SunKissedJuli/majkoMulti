package com.example.majkomulti.data.models.Task

import com.example.majkomulti.data.models.ProjectData.ProjectDataResponse
import com.example.majkomulti.data.models.UploadFiles
import com.example.majkomulti.data.models.User.CurrentUserDataResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaskDataResponse(
    @SerialName("id") val id: String?,
    @SerialName("createdAt") val createdAt: String?,
    @SerialName("updatedAt") val updatedAt: String?,
    @SerialName("title") val title: String?,
    @SerialName("text") val text: String?,
    @SerialName("priority") val priority: Int?,
    @SerialName("deadline") val deadline: String?,
    @SerialName("status") val status: Int?,
    @SerialName("image") val image: String?,
    @SerialName("creator") val creator: List<CurrentUserDataResponse>?,
    @SerialName("mainTaskId") val mainTaskId: String?,
    @SerialName("task_members") val taskMembers: List<CurrentUserDataResponse>?,
    @SerialName("is_personal") val isPersonal: Boolean?,
    @SerialName("count_subtasks") val countSubtasks: Int?,
    @SerialName("count_notes") val countNotes: Int?,
    @SerialName("count_files") val countFiles: Int?,
    @SerialName("is_favorite") val isFavorite: Boolean?,
    @SerialName("project") val project: ProjectDataResponse?,
    @SerialName("files") val files: List<UploadFiles>?,
)