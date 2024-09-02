package com.example.majkomulti.components

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@Composable
fun BlueRoundedButton(onClick: ()-> Unit, buttonText: String, modifier: Modifier = Modifier, rounded: Int = 30){
    Button(onClick = onClick,
        shape = RoundedCornerShape(rounded.dp),
        modifier = modifier.width(250.dp),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)) {
        Text(text = buttonText, color = MaterialTheme.colorScheme.background,
            fontSize = 18.sp, fontWeight = FontWeight.Medium)
    }
}