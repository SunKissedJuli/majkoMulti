package com.example.majkomulti.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FilterDropdown(
    expanded: Boolean,
    onDismissRequest: (Boolean) -> Unit,
    firstText: String,
    onClick: (Int) -> Unit,
    secondText: String,
    thirdText: String,
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { onDismissRequest(false) },
        modifier = Modifier.fillMaxWidth(0.6f)
    ) {
        DropdownMenuItem(
            text = { Text(firstText) },
            onClick = {
                onClick(1)
                onDismissRequest(false)
            }
        )

        DropdownMenuItem(
            text = { Text(secondText) },
            onClick = {
                onClick(0)
                onDismissRequest(false)
            }
        )

        DropdownMenuItem(
            text = { Text(thirdText) },
            onClick = {
                onClick(2)
                onDismissRequest(false)
            }
        )
    }
}
