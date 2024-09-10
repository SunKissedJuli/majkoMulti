package com.example.majkomulti.screen.group

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
import androidx.compose.material3.Card
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.example.majkomulti.components.AddButton
import com.example.majkomulti.components.BlueRoundedButton
import com.example.majkomulti.components.CustomCircularProgressIndicator
import com.example.majkomulti.components.FilterDropdown
import com.example.majkomulti.components.GroupCard
import com.example.majkomulti.components.SearchBox
import com.example.majkomulti.components.WhiteRoundedTextField
import com.example.majkomulti.images.MajkoResourceImages
import com.example.majkomulti.strings.MajkoResourceStrings
import kotlinx.coroutines.launch

internal actual class GroupScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { GroupViewModel() }
        LaunchedEffect(Unit) {
            launch {
                viewModel.loadData()
            }
        }

        val uiState by viewModel.stateFlow.collectAsState()
        if (viewModel.status.collectAsState().value) {
            CustomCircularProgressIndicator()
        } else {
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

                        SearchBox(
                            value = uiState.searchString,
                            onValueChange = { viewModel.updateSearchString(it, 2) },
                            placeholder = MajkoResourceStrings.group_search
                        )

                        Column {
                            Row {
                                IconButton(
                                    onClick = { viewModel.updateExpandedFilter() },
                                    Modifier.size(27.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(MajkoResourceImages.icon_filter),
                                        contentDescription = "",
                                        tint = MaterialTheme.colorScheme.background
                                    )
                                }
                            }
                            FilterDropdown(expanded = uiState.expandedFilter,
                                onDismissRequest = { viewModel.updateExpandedFilter() },
                                MajkoResourceStrings.filter_group_group,
                                { viewModel.updateSearchString(uiState.searchString, it) },
                                MajkoResourceStrings.filter_group_personal,
                                MajkoResourceStrings.filter_all
                            )
                        }

                        Spacer(modifier = Modifier.width(5.dp))

                        IconButton(onClick = {
                            viewModel.updateSearchString(
                                uiState.searchString,
                                2
                            )
                        }, Modifier.size(27.dp)) {
                            Icon(
                                painter = painterResource(MajkoResourceImages.icon_filter_off),
                                contentDescription = "", tint = MaterialTheme.colorScheme.background
                            )
                        }

                        Box(Modifier.padding(end = 10.dp)) {
                            IconButton(onClick = { viewModel.updateExpanded() }) {
                                Icon(
                                    painter = painterResource(MajkoResourceImages.icon_menu),
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.background
                                )
                            }
                            DropdownMenu(
                                expanded = uiState.expanded,
                                onDismissRequest = { viewModel.updateExpanded() },
                                modifier = Modifier.fillMaxWidth(0.5f)
                            ) {
                                Row(
                                    Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            viewModel.updateExpanded()
                                            viewModel.openInviteWindow()
                                        }) {
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
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Column(Modifier.fillMaxSize()) {
                        SetGroupScreen(uiState, viewModel)
                    }

                    Box(Modifier.align(Alignment.BottomEnd)) {
                        AddButton(onClick = { viewModel.addingGroup() })
                    }
                }
            }

        }
    }
}

@Composable
private fun SetGroupScreen(uiState: GroupState, viewModel: GroupViewModel) {
    val personalGroup = uiState.personalGroup
    val groupGroup = uiState.groupGroup

    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)) {

            if(uiState.groupGroup.isNotEmpty()) {
                item {
                    Text(
                        text = MajkoResourceStrings.group_group,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(start = 15.dp, top = 10.dp, bottom = 10.dp)
                    )
                }
            }
            items(groupGroup) { group ->
                GroupCard(groupData = group,
                    onLongTap = { viewModel.openPanel(it) },
                    onLongTapRelease = { viewModel.openPanel(it) },
                    isSelected = uiState.longtapGroupId.contains(group.id))
            }

            if(uiState.personalGroup.isNotEmpty()) {
                item {
                    Text(
                        text = MajkoResourceStrings.group_personal,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(start = 15.dp, top = 10.dp, bottom = 10.dp)
                    )
                }
            }
            items(personalGroup) { group ->
                GroupCard(groupData = group,
                    onLongTap = { viewModel.openPanel(it) },
                    onLongTapRelease = { viewModel.openPanel(it) },
                    isSelected = uiState.longtapGroupId.contains(group.id))
            }

        }
    }
}

@Composable
private fun AddGroup(uiState: GroupState,  onUpdateName: (String) -> Unit,
                     onUpdateText: (String) -> Unit, onSave: () -> Unit, onDismissRequest: () -> Unit){
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(380.dp)
                .padding(16.dp)
                .clip(RoundedCornerShape(25.dp))
                .background(MaterialTheme.colorScheme.secondary)) {

            WhiteRoundedTextField(uiState.newGroupName, onUpdateName,
                MajkoResourceStrings.group_name)

            WhiteRoundedTextField(uiState.newGroupDescription, onUpdateText,
                MajkoResourceStrings.group_description,
                Modifier
                    .fillMaxHeight(0.75f)
                    .padding(bottom = 20.dp))

            Row(Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center){
                BlueRoundedButton( onSave, MajkoResourceStrings.project_add)
            }
        }
    }
}

@Composable
private fun JoinByInviteWindow(uiState: GroupState, onUpdateInvite: (String)->Unit, onJoinByInvite: ()-> Unit,
                               onDismissRequest: () -> Unit){
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(25.dp))
                .background(MaterialTheme.colorScheme.secondary)
        ) {

            WhiteRoundedTextField(uiState.invite, onUpdateInvite,
                MajkoResourceStrings.invite, Modifier.padding(bottom = 20.dp))

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(all = 10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically){
                if(uiState.inviteMessage.equals("")){
                    BlueRoundedButton(onJoinByInvite, MajkoResourceStrings.project_joininvite)

                }else {
                    Text(text = uiState.inviteMessage, color = MaterialTheme.colorScheme.background)
                    BlueRoundedButton(onDismissRequest, MajkoResourceStrings.projectedit_close)
                }
            }
        }
    }
}

@Composable
private fun LongTapPanel(onRemoving: ()-> Unit, uiState: GroupState, onUpdateExpandedLongTap:()->Unit){
    Row(
        Modifier
            .fillMaxWidth()
            .height(65.dp)
            .background(color = MaterialTheme.colorScheme.secondary),
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End){


        Box(Modifier.padding(all = 10.dp)) {
            IconButton(onClick = onUpdateExpandedLongTap ) {
                Icon(painter = painterResource(MajkoResourceImages.icon_menu),
                    contentDescription = "", tint = MaterialTheme.colorScheme.background)
            }
            DropdownMenu(
                expanded = uiState.expandedLongTap,
                onDismissRequest = onUpdateExpandedLongTap,
                modifier = Modifier.fillMaxWidth(0.5f)) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clickable { onRemoving()
                            onUpdateExpandedLongTap()
                        }) {
                    Text(
                        MajkoResourceStrings.project_delite,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(all = 10.dp)
                    )
                }
            }
        }
    }
}



