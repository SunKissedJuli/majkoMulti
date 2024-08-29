package com.example.majkomulti.data.models.GroupData
import com.example.majkomulti.data.models.ProjectData.ProjectDataResponse
import com.example.majkomulti.data.models.ProjectData.ProjectRole
import com.example.majkomulti.data.models.User.CurrentUserDataResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroupResponse(
    @SerialName("id") val id: String?,
    @SerialName("title") val title: String?,
    @SerialName("description") val description: String?,
    @SerialName("author") val author: CurrentUserDataResponse?,
    @SerialName("created_at") val createdAt: String?,
    @SerialName("updated_at") val updatedAt: String?,
    @SerialName("parent_group") val parentGroup: String?,
    @SerialName("is_personal") val isPersonal: Boolean?,
    @SerialName("image") val image: String?,
    @SerialName("files_count") val filesCount: Int?,
    @SerialName("projects_group") val projectsGroup: List<ProjectDataResponse>?,
    @SerialName("members") val members: List<GroupMember>?,
    @SerialName("files") val files: List<String>?
)

@Serializable
data class GroupMember(
    @SerialName("id") val id: String?,
    @SerialName("user") val user: CurrentUserDataResponse?,
    @SerialName("role") val role: ProjectRole?
)