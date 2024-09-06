package com.example.majkomulti.platform.Contents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.majkomulti.components.GroupDesktopCard
import com.example.majkomulti.components.SearchBox
import com.example.majkomulti.screen.group.GroupViewModel
import com.example.majkomulti.strings.MajkoResourceStrings
import kotlinx.coroutines.launch

internal actual class GroupScreen : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        val viewModel = rememberScreenModel { GroupViewModel() }
        LaunchedEffect(Unit) {
            launch {
                viewModel.loadData()
            }
        }

        val uiState by viewModel.stateFlow.collectAsState()

        val group = uiState.personalGroup

        Column(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {

            Row(
                Modifier.fillMaxWidth().height(60.dp)
                .background(MaterialTheme.colorScheme.onSecondaryContainer),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                Row(
                    Modifier.fillMaxWidth(0.5f).height(40.dp).clip(RoundedCornerShape(30.dp))
                        .background(MaterialTheme.colorScheme.primary),
                    verticalAlignment = Alignment.CenterVertically) {
                    SearchBox(value = uiState.searchString, onValueChange = { viewModel.updateSearchString(it, 2) },
                        placeholder = MajkoResourceStrings.group_search)
                }
            }

            Row(Modifier.fillMaxSize().padding(20.dp)){
                Column(Modifier.fillMaxHeight().fillMaxWidth(0.5f)) {
                    LazyColumn(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(5.dp)) {

                        item {
                            Text(
                                text = MajkoResourceStrings.group_personal,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(
                                    start = 10.dp,
                                    top = 5.dp,
                                    bottom = 5.dp
                                )
                            )
                        }
                        if(!group.isNullOrEmpty()){
                            items(group){ group ->
                                GroupDesktopCard(
                                    navigator,
                                    groupData = group,
                                    onLongTap = { viewModel.openPanel(it) },
                                    onLongTapRelease = { viewModel.openPanel(it) },
                                    isSelected = uiState.longtapGroupId.contains(group.id)
                                )
                            }
                        }
                    }
                }
            }

        }
    }
}