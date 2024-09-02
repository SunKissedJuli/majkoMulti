package com.example.majkomulti.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.example.majkomulti.strings.MajkoResourceStrings

@Composable
fun ExitAlertDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = MajkoResourceStrings.common_exit_title) },
        text = { Text(text = MajkoResourceStrings.common_exit_text) },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(text = MajkoResourceStrings.common_yes)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = MajkoResourceStrings.common_no)
            }
        }
    )
}