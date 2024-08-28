package com.example.majkomulti.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun AddButton(onClick: (String) -> Unit, id: String){

    IconButton(onClick = { onClick(id)},
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 25.dp)
            .size(56.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)
    ) {
       // Image(painter = painterResource(R.drawable.icon_plus), contentDescription = "")
        Icon(Icons.Filled.Add,
            tint = MaterialTheme.colorScheme.background,
            contentDescription = "")
    }
}