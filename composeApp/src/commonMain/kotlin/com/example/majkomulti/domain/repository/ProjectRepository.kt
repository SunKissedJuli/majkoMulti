package com.example.majkomulti.domain.repository

import com.example.majkomulti.data.models.ProjectData.JoinByInviteProjectData
import com.example.majkomulti.data.models.ProjectData.ProjectById
import com.example.majkomulti.data.models.ProjectData.ProjectByIdUnderscore
import com.example.majkomulti.data.models.ProjectData.ProjectData
import com.example.majkomulti.data.models.ProjectData.ProjectUpdate
import com.example.majkomulti.data.models.Task.SearchTask
import com.example.majkomulti.domain.modelsUI.MessageDataUi
import com.example.majkomulti.domain.modelsUI.Project.ProjectCreateInviteUi
import com.example.majkomulti.domain.modelsUI.Project.ProjectCurrentUi
import com.example.majkomulti.domain.modelsUI.Project.ProjectDataUi
import com.example.majkomulti.platform.Either
import com.example.majkomulti.platform.Failure
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {

    suspend fun getPersonalProject(search: SearchTask): Either<Failure, List<ProjectDataUi>>

    suspend fun getGroupProject(search: SearchTask): Either<Failure, List<ProjectDataUi>>

    suspend fun postNewProject(project: ProjectData):Either<Failure, ProjectDataUi>

    suspend fun getProjectById(projectById: ProjectById): Either<Failure, ProjectCurrentUi>

    suspend fun updateProject(project: ProjectUpdate): Either<Failure, ProjectCurrentUi>

    suspend fun removeProject(projectId: ProjectById): Either<Failure, Unit>

    suspend fun createInvitetoProject(projectById: ProjectByIdUnderscore): Either<Failure, ProjectCreateInviteUi>

    suspend fun joinByInvitation(invite: JoinByInviteProjectData): Either<Failure, MessageDataUi>

}