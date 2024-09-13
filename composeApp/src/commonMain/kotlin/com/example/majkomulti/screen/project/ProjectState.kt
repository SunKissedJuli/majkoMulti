package com.example.majkomulti.screen.project

import com.example.majkomulti.commons.Constantas.DEFAULT_BOOLEAN
import com.example.majkomulti.commons.Constantas.DEFAULT_STRING
import com.example.majkomulti.domain.modelsUI.Project.ProjectDataUi

data class ProjectState(
    val personalProject: List<ProjectDataUi>,
    val groupProject: List<ProjectDataUi>,
    val personalActiveProject: List<ProjectDataUi>,
    val personalDisactiveProject: List<ProjectDataUi>,
    val groupActiveProject: List<ProjectDataUi>,
    val groupDisactiveProject: List<ProjectDataUi>,
    val searchString: String,
    val isAdding: Boolean,
    val newProjectName: String,
    val newProjectDescription: String,
    val isLongtap: Boolean,
    val longtapProjectId: String,
    val isInvite: Boolean,
    val invite: String,
    val invite_message: String,
    val isError: Boolean,
    val errorMessage: Int?,
    val isMessage: Boolean,
    val message: Int?,
    val expandedFilter: Boolean,
    val expanded: Boolean,
    val expandedLongTap: Boolean
) {
    companion object {

        val InitState = ProjectState(
            personalProject = emptyList(),
            groupProject = emptyList(),
            personalActiveProject = emptyList(),
            personalDisactiveProject = emptyList(),
            groupActiveProject = emptyList(),
            groupDisactiveProject = emptyList(),
            searchString = DEFAULT_STRING,
            isAdding = DEFAULT_BOOLEAN,
            newProjectName = DEFAULT_STRING,
            newProjectDescription = DEFAULT_STRING,
            isLongtap = DEFAULT_BOOLEAN,
            longtapProjectId = DEFAULT_STRING,
            isInvite = DEFAULT_BOOLEAN,
            invite = DEFAULT_STRING,
            invite_message = DEFAULT_STRING,
            isError = DEFAULT_BOOLEAN,
            errorMessage = null,
            isMessage = DEFAULT_BOOLEAN,
            message = null,
            expandedFilter = DEFAULT_BOOLEAN,
            expanded = DEFAULT_BOOLEAN,
            expandedLongTap = DEFAULT_BOOLEAN
        )
    }
}
