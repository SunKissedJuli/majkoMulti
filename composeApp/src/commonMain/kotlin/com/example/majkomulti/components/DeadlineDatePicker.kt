package com.example.majkomulti.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.majkomulti.strings.MajkoResourceStrings
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeadlineDatePicker(
    currentDeadline: String,
    onUpdateDeadline: (String) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    val formattedDisplayDate = remember(currentDeadline) {
        if (currentDeadline.isNotEmpty()) {
            val dateTime = LocalDateTime.parse(currentDeadline, DateTimeFormatter.ofPattern("yyyy-M-d H:m:s"))
            dateTime.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale("ru")) +
                    ", " + dateTime.dayOfMonth + " " +
                    dateTime.month.getDisplayName(TextStyle.FULL, Locale("ru"))

        } else{ currentDeadline }
    }

    Text(text = MajkoResourceStrings.taskeditor_deadline + " " + formattedDisplayDate,
        fontSize = 18.sp, modifier = Modifier.clickable { showDialog = true })

    if (showDialog) {
        DatePickerDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let { millis ->
                        val instant = Instant.ofEpochMilli(millis)
                        val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
                        val formattedSaveDate = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-M-d H:m:s"))
                        onUpdateDeadline(formattedSaveDate)
                    }
                    showDialog = false }) {
                    Text(MajkoResourceStrings.message_ok)
                }
            },
            dismissButton = { TextButton(onClick = { showDialog = false }) {
                Text(MajkoResourceStrings.common_cansel) }
            }) {
            DatePicker(state = datePickerState)
        }
    }
}