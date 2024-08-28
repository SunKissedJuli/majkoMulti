package com.example.majkomulti.components

import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LineTextField(value: String, onValueChange: (String)-> Unit, placeholder: String = "", modifier: Modifier = Modifier){
    TextField(
        value = value,
        modifier = modifier.height(55.dp),
        placeholder = { Text(text = placeholder, color = MaterialTheme.colorScheme.onSurface) },
        onValueChange = { onValueChange(it) },
        colors = TextFieldDefaults.colors(focusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedContainerColor = MaterialTheme.colorScheme.background)
    )
}