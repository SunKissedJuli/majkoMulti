package com.example.majkomulti.domain.repository

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
import com.example.majkomulti.platform.Either
import com.example.majkomulti.platform.Failure
import kotlinx.coroutines.flow.Flow

interface GroupRepository {

    suspend fun addGroup(group: GroupData) : Either<Failure, GroupUi>

    suspend fun getPersonalGroup(search: SearchTask) : Either<Failure,List<GroupUi>>

    suspend fun getGroupGroup(search: SearchTask) : Either<Failure,List<GroupUi>>

    suspend fun getGroupById(groupId: GroupById) : Either<Failure,GroupUi>

    suspend fun updateGroup(group: GroupUpdate) : Either<Failure, GroupUi>

    suspend fun removeGroup(groupId: GroupById) : Either<Failure,Unit>

    suspend fun addProjectInGroup(group: ProjectInGroup) : Either<Failure, ProjectDataUi>

    suspend fun createInvitetoGroup(groupId: GroupByIdUnderscore) : Either<Failure, GroupInviteUi>

    suspend fun joinGroupByInvitation(invite: JoinByInviteProjectData) : Either<Failure, MessageDataUi>
}