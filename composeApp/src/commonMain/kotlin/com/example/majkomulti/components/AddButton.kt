package com.example.majkomulti.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun AddButton(onClick: () -> Unit){

    Box(modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 25.dp)
            .size(56.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)
            .clickable{onClick()}) {

       // Image(painter = painterResource(R.drawable.icon_plus), contentDescription = "")
        Icon(Icons.Filled.Add,
            tint = MaterialTheme.colorScheme.background,
            contentDescription = "",
            modifier = Modifier.align(Alignment.Center))
    }
}