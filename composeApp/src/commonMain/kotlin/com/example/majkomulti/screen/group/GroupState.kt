package com.example.majkomulti.screen.group

import com.example.majkomulti.domain.modelsUI.Group.GroupUi

data class GroupState(
    val personalGroup : List<GroupUi> = emptyList(),
    val groupGroup : List<GroupUi> = emptyList(),
    val personalActiveGroup : List<GroupUi> = emptyList(),
    val groupActiveGroup : List<GroupUi> = emptyList(),
    val personalDisactiveGroup : List<GroupUi> = emptyList(),
    val groupDesactiveGroup : List<GroupUi> = emptyList(),
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
