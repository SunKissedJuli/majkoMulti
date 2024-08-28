package com.example.majkomulti.data.models.ProjectData

import com.example.majkomulti.data.models.Task.TaskDataResponse
import com.example.majkomulti.data.models.User.CurrentUserDataResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class ProjectCurrentResponse(
    @SerialName("id") val id: String?,
    @SerialName("createdAt") val createdAt: String?,
    @SerialName("updatedAt") val updatedAt: String?,
    @SerialName("name") val name: String?,
    @SerialName("description") val description: String?,
    @SerialName("is_archive") var isArchive: Int?,
    @SerialName("author") val author: CurrentUserDataResponse?,
    @SerialName("members") val members: List<Member>?,
    @SerialName("image") val image: String?,
    @SerialName("is_personal") var isPersonal: Boolean?,
    @SerialName("count_files") val countFiles: Int?,
    @SerialName("tasks") val tasks: List<TaskDataResponse>?,
    @SerialName("groups") val groups: List<Group>?,
    @SerialName("files") val files: List<File>?
)

@Serializable
data class Group(
    @SerialName("id") val id: String?,
    @SerialName("name") val name: String?
)

@Serializable
data class File(
    @SerialName("id") val id: String?,
    @SerialName("name") val name: String?,
    @SerialName("url") val url: String?
)

@Serializable
data class Member(
    @SerialName("project_member_id") var projectMemberId: String?,
    @SerialName("user") val user: CurrentUserDataResponse?,
    @SerialName("role_id") var roleId:ProjectRole?
)

@Serializable
data class ProjectRole(
    @SerialName("id") val id: Int?,
    @SerialName("name") val name: String?,
    @SerialName("created_at") val createdAt: String?,
    @SerialName("updated_at") val updatedAt: String?,
)
