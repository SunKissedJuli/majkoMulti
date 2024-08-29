package com.example.majkomulti.data.mapper

import com.example.majkomulti.data.models.GroupData.GroupInviteResponse
import com.example.majkomulti.domain.modelsUI.Group.GroupInviteUi

fun GroupInviteResponse.toUI(): GroupInviteUi {
    return GroupInviteUi(
        groupId = this.groupId.orEmpty(),
        invite = this.invite.orEmpty(),
        userId = this.userId.orEmpty(),
        id = this.id.orEmpty(),
        updatedAt = this.updatedAt.orEmpty(),
        createdAt = this.createdAt.orEmpty()
    )
}