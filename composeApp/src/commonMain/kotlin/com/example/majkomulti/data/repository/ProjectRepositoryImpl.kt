package com.example.majkomulti.data.repository

import com.example.majkomulti.data.api.MajkoApi
import com.example.majkomulti.data.mapper.toUI
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
import com.example.majkomulti.domain.repository.ProjectRepository
import com.example.majkomulti.platform.Either
import com.example.majkomulti.platform.Failure
import com.example.majkomulti.platform.apiCall

class ProjectRepositoryImpl(private val majkoApi: MajkoApi): ProjectRepository {

    override suspend fun getPersonalProject(search: SearchTask): Either<Failure, List<ProjectDataUi>> {
        return apiCall (call = {
            majkoApi.getPersonalProject(search)
        },
            mapResponse = { info -> info.toUI() })
    }

    override suspend fun getGroupProject(search: SearchTask): Either<Failure, List<ProjectDataUi>> {
        return apiCall (call = {
            majkoApi.getGroupProject(search)
        },
            mapResponse = { info -> info.toUI() })
    }

    override suspend fun postNewProject(project: ProjectData): Either<Failure, ProjectDataUi> {
        return apiCall (call = {
            majkoApi.postNewProject(project)
        },
            mapResponse = { info -> info.toUI() })
    }

    override suspend fun getProjectById(projectById: ProjectById): Either<Failure, ProjectCurrentUi> {
        return apiCall (call = {
            majkoApi.getProjectById(projectById)
        },
            mapResponse = { info -> info.toUI() })
    }

    override suspend fun updateProject(project: ProjectUpdate): Either<Failure, ProjectCurrentUi> {
        return apiCall (call = {
            majkoApi.updateProject(project)
        },
            mapResponse = { info -> info.toUI() })
    }

    override suspend fun removeProject(projectId: ProjectById): Either<Failure, Unit> {
        return apiCall (call = {
            majkoApi.removeProject(projectId)
        },
            mapResponse = { Unit })
    }

    override suspend fun createInvitetoProject(projectById: ProjectByIdUnderscore): Either<Failure, ProjectCreateInviteUi> {
        return apiCall (call = {
            majkoApi.createInvitetoProject(projectById)
        },
            mapResponse = { info -> info.toUI() })
    }

    override suspend fun joinByInvitation(invite: JoinByInviteProjectData): Either<Failure, MessageDataUi> {
        return apiCall (call = {
            majkoApi.joinByInvitation(invite)
        },
            mapResponse = { info -> info.toUI() })
    }

}