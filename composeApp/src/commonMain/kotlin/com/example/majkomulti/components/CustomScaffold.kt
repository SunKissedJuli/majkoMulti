package com.example.majkomulti.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp

@Composable
fun CustomScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable (() -> Unit)? = null,
    bottomBar: @Composable (() -> Unit)? = null,
    leftBar: @Composable (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val localFocusManager = LocalFocusManager.current


    /*    Box(modifier = Modifier.fillMaxHeight()) {
            Card(
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 18.dp),
                shape = RoundedCornerShape(0.dp),
             //   colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest)
            ) {
                leftBar?.let { left ->
                    left()
                }
            }
        }*/

    Row {
        Box(modifier = Modifier.fillMaxHeight()) {
            leftBar?.let { left ->
                left()
            }
        }
        Column {
            Scaffold(containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onSecondary,
                modifier = modifier.weight(1f).pointerInput(Unit) {
                    detectTapGestures(onTap = { localFocusManager.clearFocus() })
                },
                topBar = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        topBar?.let { top ->
                            top()
                        }
                    }
                },
                content = {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                            .padding(
                                top = it.calculateTopPadding(),
                                bottom = it.calculateBottomPadding()
                            )
                    ) {
                        content()
                    }
                }
            )
            Box(modifier = Modifier.fillMaxWidth()) {
                bottomBar?.let { bottom ->
                    bottom()
                }
            }
        }
    }
}