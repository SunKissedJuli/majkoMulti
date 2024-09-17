package com.example.majkomulti.data.repository

import com.example.majkomulti.data.api.MajkoApi
import com.example.majkomulti.data.mapper.toUI
import com.example.majkomulti.data.models.GroupData.GroupById
import com.example.majkomulti.data.models.GroupData.GroupByIdUnderscore
import com.example.majkomulti.data.models.GroupData.GroupData
import com.example.majkomulti.data.models.GroupData.GroupUpdate
import com.example.majkomulti.data.models.GroupData.ProjectInGroup
import com.example.majkomulti.data.models.ProjectData.JoinByInviteProjectData
import com.example.majkomulti.data.models.Task.SearchTask
import com.example.majkomulti.domain.modelsUI.Group.GroupInviteUi
import com.example.majkomulti.domain.modelsUI.Group.GroupUi
import com.example.majkomulti.domain.modelsUI.MessageDataUi
import com.example.majkomulti.domain.modelsUI.Project.ProjectDataUi
import com.example.majkomulti.domain.repository.GroupRepository
import com.example.majkomulti.platform.Either
import com.example.majkomulti.platform.Failure
import com.example.majkomulti.platform.apiCall

class GroupRepositoryImpl (private val majkoApi: MajkoApi): GroupRepository{

    override suspend fun addGroup(group: GroupData): Either<Failure, GroupUi> {
        return apiCall (call = {
            majkoApi.addGroup(group)
        },
            mapResponse = { userData -> userData.toUI() })
    }

    override suspend fun getPersonalGroup(search: SearchTask): Either<Failure, List<GroupUi>> {
        return apiCall (call = {
            majkoApi.getPersonalGroup(search)
        },
            mapResponse = { userData -> userData.toUI() })
    }

    override suspend fun getGroupGroup(search: SearchTask): Either<Failure, List<GroupUi>> {
        return apiCall (call = {
            majkoApi.getGroupGroup(search)
        },
            mapResponse = { userData -> userData.toUI() })
    }

    override suspend fun getGroupById(groupId: GroupById): Either<Failure, GroupUi> {
        return apiCall (call = {
            majkoApi.getGroupById(groupId)
        },
            mapResponse = { userData -> userData.toUI() })
    }

    override suspend fun updateGroup(group: GroupUpdate): Either<Failure, GroupUi> {
        return apiCall (call = {
            majkoApi.updateGroup(group)
        },
            mapResponse = { userData -> userData.toUI() })
    }

    override suspend fun removeGroup(groupId: GroupById): Either<Failure, Unit> {
        return apiCall (call = {
            majkoApi.removeGroup(groupId)
        })
    }

    override suspend fun addProjectInGroup(group: ProjectInGroup): Either<Failure, ProjectDataUi> {
        return apiCall (call = {
            majkoApi.addProjectInGroup(group)
        },
            mapResponse = { userData -> userData.toUI() })
    }

    override suspend fun createInvitetoGroup(groupId: GroupByIdUnderscore): Either<Failure, GroupInviteUi> {
        return apiCall (call = {
            majkoApi.createInvitetoGroup(groupId)
        },
            mapResponse = { userData -> userData.toUI() })
    }

    override suspend fun joinGroupByInvitation(invite: JoinByInviteProjectData): Either<Failure, MessageDataUi> {
        return apiCall (call = {
            majkoApi.joinGroupByInvitation(invite)
        },
            mapResponse = { userData -> userData.toUI() })
    }

}