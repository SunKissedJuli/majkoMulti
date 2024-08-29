package com.example.majkomulti.screen.project

import com.example.majkomulti.domain.modelsUI.Project.ProjectDataUi

data class ProjectState(
    val personalProject : List<ProjectDataUi>? = listOf(),
    val groupProject : List<ProjectDataUi>? = listOf(),
    val searchPersonalProject : List<ProjectDataUi>? = listOf(),
    val searchGroupProject : List<ProjectDataUi>? = listOf(),
    val searchString: String = DEFAULT_STRING,
    val isAdding: Boolean = DEFAULT_BOOLEAN,
    val newProjectName : String = DEFAULT_STRING,
    val newProjectDescription : String = DEFAULT_STRING,
    val isLongtap: Boolean = DEFAULT_BOOLEAN,
    val longtapProjectId: String = DEFAULT_STRING,
    val isInvite: Boolean =  DEFAULT_BOOLEAN,
    val invite: String = DEFAULT_STRING,
    val invite_message: String = DEFAULT_STRING,
    val isError: Boolean = DEFAULT_BOOLEAN,
    val errorMessage: Int? = null,
    val isMessage: Boolean = DEFAULT_BOOLEAN,
    val message: Int? = null,
    val expandedFilter: Boolean = DEFAULT_BOOLEAN,
    val expanded: Boolean = DEFAULT_BOOLEAN,
    val expandedLongTap: Boolean = DEFAULT_BOOLEAN
){
companion object{
    const val DEFAULT_STRING = ""
    const val DEFAULT_BOOLEAN = false

    fun InitState() = ProjectState()
}
}
