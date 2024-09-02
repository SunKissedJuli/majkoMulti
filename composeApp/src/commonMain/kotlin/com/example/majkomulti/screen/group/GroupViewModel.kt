package com.example.majkomulti.screen.group

import androidx.lifecycle.viewModelScope
import com.example.majkomulti.data.models.Task.SearchTask
import com.example.majkomulti.domain.modelsUI.Group.GroupUi
import com.example.majkomulti.domain.repository.GroupRepository
import com.example.majkomulti.platform.BaseScreenModel
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

internal class GroupViewModel: BaseScreenModel<GroupState, Unit>(GroupState.InitState()) {

    private val groupRepository: GroupRepository by inject()

    fun updateGroupName(name: String)= blockingIntent {
        reduce { state.copy(newGroupName = name) }
    }

    fun updateGroupDescription(description: String)= blockingIntent {
        reduce { state.copy(newGroupDescription = description) }
    }

    fun updateInvite(invite: String) = blockingIntent {
        reduce { state.copy(invite = invite) }
    }

    fun addingGroup(){

    }

    fun openInviteWindow(){

    }

    fun openPanel(id: String) {

    }

    fun isError(message: Int?) {

    }

    fun isMessage(message: Int?) {

    }

    fun updateSearchString(newSearchString:String, whatFilter: Int) = blockingIntent {
        reduce { state.copy(searchString = newSearchString) }
        loadData(newSearchString)
    }

    fun updateExpandedFilter(){

    }

    fun updateExpanded(){

    }

    fun updateExpandedLongTap(){

    }

    fun removeGroup() {

    }


    fun addGroup(){

    }

    fun loadData(search: String = "") {
        loadPersonalGroup(search)
        loadGroupGroup(search)
    }

    private fun loadGroupGroup(search: String) = intent {
        launchOperation(
            operation = {
                groupRepository.getGroupGroup(SearchTask(search))
            },
            success = { response ->
                val validData: MutableList<GroupUi> = mutableListOf()
                response.forEach { item ->
                    if (!item.isPersonal) {
                        validData.add(item)
                    }
                }
                reduceLocal { state.copy(groupGroup = validData) }

            }
        )
    }

    private fun loadPersonalGroup(search: String)= intent {
        launchOperation(
            operation = {
                groupRepository.getPersonalGroup(SearchTask(search))
            },
            success = { response ->
                val validData: MutableList<GroupUi> = mutableListOf()
                response.forEach { item ->
                    if (item.isPersonal) {
                        validData.add(item)
                    }
                }
                reduceLocal { state.copy(personalGroup = validData) }

            }
        )
    }

    fun joinByInvite(){

    }
}