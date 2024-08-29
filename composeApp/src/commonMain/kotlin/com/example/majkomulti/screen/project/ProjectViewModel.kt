package com.example.majkomulti.screen.project

import androidx.lifecycle.viewModelScope
import com.example.majkomulti.data.models.Task.SearchTask
import com.example.majkomulti.domain.modelsUI.Project.ProjectDataUi
import com.example.majkomulti.domain.repository.ProjectRepository
import com.example.majkomulti.platform.BaseScreenModel
import com.example.majkomulti.screen.login.LoginState
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

internal class ProjectViewModel : BaseScreenModel<ProjectState, Unit>(ProjectState.InitState()) {

    val projectRepository: ProjectRepository by inject()

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

    fun loadPersonalProjectData(search: String = ""){
        loadAllPersonalProject(search)
    }

    fun loadGroupProjectData(search: String = ""){
        loadAllGroupProject(search)
    }

    private fun loadPersonalProject(search: String) = intent {
        launchOperation(
            operation = {
                projectRepository.getPersonalProject(SearchTask(search))
            },
            success = { response ->
                val validData: MutableList<ProjectDataUi> = mutableListOf()
                response.forEach { item ->
                    if (item.isPersonal && item.isArchive==0) {
                        validData.add(item)
                    }
                }
                reduceLocal { state.copy(personalProject = validData, searchPersonalProject = validData) }
            }
        )
    }

    private fun loadAllGroupProject(search: String) = intent {
        launchOperation(
            operation = {
                projectRepository.getGroupProject(SearchTask(search))
            },
            success = { response ->
                val groupActiveProject: MutableList<ProjectDataUi> = mutableListOf()
                val groupDisactiveProject: MutableList<ProjectDataUi> = mutableListOf()
                response.forEach { item ->
                    if (!item.isPersonal && item.isArchive==0) {
                        groupActiveProject.add(item)
                    }else if(!item.isPersonal && item.isArchive==1){
                        groupDisactiveProject.add(item)
                    }
                }
                reduceLocal { state.copy(groupActiveProject = groupActiveProject, groupDisactiveProject = groupDisactiveProject) }
            }
        )
    }

    private fun loadAllPersonalProject(search: String) = intent {
        launchOperation(
            operation = {
                projectRepository.getPersonalProject(SearchTask(search))
            },
            success = { response ->
                val personalActiveProject: MutableList<ProjectDataUi> = mutableListOf()
                val personalDisactiveProject: MutableList<ProjectDataUi> = mutableListOf()
                response.forEach { item ->
                    if (item.isPersonal && item.isArchive==0) {
                        personalActiveProject.add(item)
                    }else if(item.isPersonal && item.isArchive==1){
                        personalDisactiveProject.add(item)
                    }
                }
                reduceLocal { state.copy(personalActiveProject = personalActiveProject, personalDisactiveProject = personalDisactiveProject) }
            }
        )
    }


    private fun loadGroupProject(search: String) = intent {
        launchOperation(
            operation = {
                projectRepository.getGroupProject(SearchTask(search))
            },
            success = { response ->
                val validData: MutableList<ProjectDataUi> = mutableListOf()
                response.forEach { item ->
                    if (!item.isPersonal && item.isArchive==0) {
                        validData.add(item)
                    }
                }
                reduceLocal { state.copy(personalProject = validData, searchPersonalProject = validData) }
            }
        )
    }
}