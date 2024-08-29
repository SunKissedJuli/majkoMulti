package com.example.majkomulti.screen.project

import androidx.lifecycle.viewModelScope
import com.example.majkomulti.platform.BaseScreenModel
import com.example.majkomulti.screen.login.LoginState
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.reduce

internal class ProjectViewModel : BaseScreenModel<ProjectState, Unit>(ProjectState.InitState()) {

    fun updateProjectName(name: String){

    }

    fun updateProjectDescription(description: String){

    }

    fun updateInvite(invite: String){

    }

    fun openInviteWindow(){

    }

    fun addingProject(){

    }

    fun notAddingProjectYet(){

    }

    fun openPanel(id: String) {

    }


    fun updateSearchString(newSearchString:String, whatFilter: Int) = blockingIntent {
        reduce { state.copy(searchString = newSearchString) }
    }


    fun updateExpandedFilter(){

    }

    fun updateExpanded(){

    }

    fun updateExpandedLongTap(){

    }

    fun joinByInvite(){

    }

    fun toArchive() {

    }

    fun removeProjects() {

    }

    fun addProject(){

    }

    fun loadData(search: String = ""){
        loadPersonalProject(search)
        loadGroupProject(search)
    }

    private fun loadPersonalProject(search: String){

    }

    private fun loadGroupProject(search: String) {
    }
}