package com.example.majkomulti.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ContentAlpha
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun SimpleTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle(),
    placeholderStyle: TextStyle = TextStyle(
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.medium)
    )
) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            textStyle = style,
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = placeholderStyle
                        )
                    }
                    innerTextField()
                }
            }
        )
}