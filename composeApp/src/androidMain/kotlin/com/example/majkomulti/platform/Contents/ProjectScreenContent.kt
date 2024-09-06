package com.example.majkomulti.platform.Contents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.majkomulti.components.AddButton
import com.example.majkomulti.components.FilterDropdown
import com.example.majkomulti.components.ProjectCard
import com.example.majkomulti.components.SearchBox
import com.example.majkomulti.images.MajkoResourceImages
import com.example.majkomulti.screen.project.ProjectState
import com.example.majkomulti.screen.project.ProjectViewModel
import com.example.majkomulti.strings.MajkoResourceStrings
import kotlinx.coroutines.launch
import io.github.skeptick.libres.compose.painterResource


internal actual class ProjectScreen : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        val viewModel = rememberScreenModel { ProjectViewModel() }
        LaunchedEffect(Unit){
            launch {
                viewModel.loadData()
            }
        }

        val uiState by viewModel.stateFlow.collectAsState()
        Scaffold(
            topBar = {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(65.dp)
                        .padding(all = 10.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .background(color = MaterialTheme.colorScheme.primary),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    SearchBox(uiState.searchString, { viewModel.updateSearchString(it, 2) }, MajkoResourceStrings.project_search)

                    Column {
                        Row {
                            IconButton(onClick = { viewModel.updateExpandedFilter() }, Modifier.size(27.dp)) {
                                Icon(painter = painterResource(MajkoResourceImages.icon_filter),
                                    contentDescription = "", tint = MaterialTheme.colorScheme.background)
                            }
                        }
                        FilterDropdown(expanded = uiState.expandedFilter,
                            onDismissRequest = { viewModel.updateExpandedFilter() }, MajkoResourceStrings.filter_project_group,
                            { viewModel.updateSearchString(uiState.searchString, it) },
                            MajkoResourceStrings.filter_group_personal, MajkoResourceStrings.filter_all)
                    }

                    Spacer(modifier = Modifier.width(5.dp))

                    IconButton(onClick = { viewModel.updateSearchString(uiState.searchString, 2) }, Modifier.size(27.dp)) {
                        Icon(painter = painterResource(MajkoResourceImages.icon_filter_off),
                            contentDescription = "", tint = MaterialTheme.colorScheme.background)
                    }

                    Box(Modifier.padding(end = 10.dp)) {
                        IconButton(onClick = { viewModel.updateExpanded() }) {
                            Icon(painter = painterResource(MajkoResourceImages.icon_menu),
                                contentDescription = "", tint = MaterialTheme.colorScheme.background)
                        }
                        DropdownMenu(
                            expanded = uiState.expanded,
                            onDismissRequest = { viewModel.updateExpanded() },
                            modifier = Modifier.fillMaxWidth(0.5f)
                        ) {

                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .clickable { viewModel.openInviteWindow()
                                        viewModel.updateExpanded()}) {
                                Text(
                                    MajkoResourceStrings.project_joininvite,
                                    fontSize = 18.sp,
                                    modifier = Modifier.padding(all = 10.dp)
                                )
                            }
                        }
                    }
                }
            }
        ) {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(MaterialTheme.colorScheme.background)) {
                Column(Modifier.fillMaxSize()) {
                    SetProjectScreen(uiState, navigator, viewModel)
                }

                Box(Modifier.align(Alignment.BottomEnd)){
                    AddButton(onClick = {viewModel.addingProject()}, id = "")
                }
            }
        }

    }
}

@Composable
private fun SetProjectScreen(uiState: ProjectState, navigator: Navigator, viewModel: ProjectViewModel) {
    val personalProject = uiState.personalProject
    val groupProject = uiState.groupProject

    Column(Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {

        LazyColumn(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)) {
            if (!groupProject.isNullOrEmpty()) {
                item {
                    Text(text = MajkoResourceStrings.project_group,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(start = 15.dp, top = 10.dp, bottom = 10.dp))
                }
                items(groupProject) { project ->
                    ProjectCard(
                        navigator,
                        projectData = project,
                        onLongTap = { viewModel.openPanel(it) },
                        onLongTapRelease = { viewModel.openPanel(it) },
                        isSelected = uiState.longtapProjectId.contains(project.id)
                    )
                }
            }

            if (!personalProject.isNullOrEmpty()) {
                item {
                    Text(text = MajkoResourceStrings.project_personal,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(start = 15.dp, top = 10.dp, bottom = 10.dp))
                }
                items(personalProject) { project ->
                    ProjectCard(
                        navigator,
                        projectData = project,
                        onLongTap = { viewModel.openPanel(it) },
                        onLongTapRelease = { viewModel.openPanel(it) },
                        isSelected = uiState.longtapProjectId.contains(project.id)
                    )
                }
            }
        }
    }
}