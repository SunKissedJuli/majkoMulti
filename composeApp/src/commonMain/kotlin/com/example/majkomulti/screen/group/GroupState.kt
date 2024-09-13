package com.example.majkomulti.screen.group

import com.example.majkomulti.commons.Constantas.DEFAULT_BOOLEAN
import com.example.majkomulti.commons.Constantas.DEFAULT_STRING
import com.example.majkomulti.domain.modelsUI.Group.GroupUi

data class GroupState(
    val personalGroup: List<GroupUi>,
    val groupGroup: List<GroupUi>,
    val personalActiveGroup: List<GroupUi>,
    val groupActiveGroup: List<GroupUi>,
    val personalDisactiveGroup: List<GroupUi>,
    val groupDesactiveGroup: List<GroupUi>,
    val searchString: String,
    val isAdding: Boolean,
    val newGroupName: String,
    val newGroupDescription: String,
    val isInvite: Boolean,
    val invite: String,
    val inviteMessage: String,
    val isError: Boolean,
    val errorMessage: Int?,
    val isMessage: Boolean,
    val message: Int?,
    val isLongtap: Boolean,
    val longtapGroupId: String,
    val expandedFilter: Boolean,
    val expanded: Boolean,
    val expandedLongTap: Boolean
) {
    companion object {

        val InitState = GroupState(
            personalGroup = emptyList(),
            groupGroup = emptyList(),
            personalActiveGroup = emptyList(),
            groupActiveGroup = emptyList(),
            personalDisactiveGroup = emptyList(),
            groupDesactiveGroup = emptyList(),
            searchString = DEFAULT_STRING,
            isAdding = DEFAULT_BOOLEAN,
            newGroupName = DEFAULT_STRING,
            newGroupDescription = DEFAULT_STRING,
            isInvite = DEFAULT_BOOLEAN,
            invite = DEFAULT_STRING,
            inviteMessage = DEFAULT_STRING,
            isError = DEFAULT_BOOLEAN,
            errorMessage = null,
            isMessage = DEFAULT_BOOLEAN,
            message = null,
            isLongtap = DEFAULT_BOOLEAN,
            longtapGroupId = DEFAULT_STRING,
            expandedFilter = DEFAULT_BOOLEAN,
            expanded = DEFAULT_BOOLEAN,
            expandedLongTap = DEFAULT_BOOLEAN
        )
    }
}