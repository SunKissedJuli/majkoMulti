package com.example.majkomulti.screen.group

import com.example.majkomulti.domain.modelsUI.Group.GroupUi

data class GroupState(
    val personalGroup : List<GroupUi>? = listOf(),
    val groupGroup : List<GroupUi>? = listOf(),
    val personalActiveGroup : List<GroupUi>? = listOf(),
    val groupActiveGroup : List<GroupUi>? = listOf(),
    val personalDisactiveGroup : List<GroupUi>? = listOf(),
    val groupDesactiveGroup : List<GroupUi>? = listOf(),
    val searchString: String = DEFAULT_STRING,
    val isAdding: Boolean = DEFAULT_BOOLEAN,
    val newGroupName : String = DEFAULT_STRING,
    val newGroupDescription : String = DEFAULT_STRING,
    val isInvite: Boolean =  DEFAULT_BOOLEAN,
    val invite: String = DEFAULT_STRING,
    val inviteMessage: String = DEFAULT_STRING,
    val isError: Boolean = DEFAULT_BOOLEAN,
    val errorMessage: Int? = null,
    val isMessage: Boolean = DEFAULT_BOOLEAN,
    val message: Int? = null,
    val isLongtap: Boolean = DEFAULT_BOOLEAN,
    val longtapGroupId: String = DEFAULT_STRING,
    val expandedFilter: Boolean = DEFAULT_BOOLEAN,
    val expanded: Boolean = DEFAULT_BOOLEAN,
    val expandedLongTap: Boolean = DEFAULT_BOOLEAN,
) {
    companion object {
        const val DEFAULT_STRING = ""
        const val DEFAULT_BOOLEAN = false

        fun InitState() = GroupState()
    }
}
