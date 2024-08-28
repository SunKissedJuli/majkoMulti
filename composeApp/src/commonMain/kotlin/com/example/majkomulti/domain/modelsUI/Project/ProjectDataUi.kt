package com.example.majkomulti.domain.modelsUI.Project

import com.example.majkomulti.data.models.ProjectData.Member
import com.example.majkomulti.domain.modelsUI.User.CurrentUserDataUi

data class ProjectDataUi(
    val id: String,
    val createdAt: String,
    val updatedAt: String,
    val name: String,
    val description: String,
    var isArchive: Int,
    val author: CurrentUserDataUi,
    val members: List<Member>,
    val image: String,
    var isPersonal: Boolean,
    val countFiles: Int,
){
    companion object{
        fun empty() = ProjectDataUi(
            id = "",
            createdAt = "",
            updatedAt = "",
            name = "",
            description = "",
            isArchive = 0,
            author = CurrentUserDataUi.empty(),
            members = listOf(),
            image = "",
            isPersonal = true,
            countFiles = 0
        )
    }
}