package com.example.majkomulti.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.majkomulti.images.MajkoResourceImages
import io.github.skeptick.libres.compose.painterResource

@Composable
fun ButtonBack(onClick: ()-> Unit = {}){
    IconButton(onClick = onClick) {
        Icon(painter = painterResource(MajkoResourceImages.icon_back), contentDescription = "",
            tint = MaterialTheme.colorScheme.background)
    }
}

@Composable
fun ButtonBackWithArgunemt(onClick: (String)-> Unit = {}){
    IconButton(onClick = {onClick("")}, modifier = Modifier.width(80.dp).padding(top = 10.dp)) {
        Icon(painter = painterResource(MajkoResourceImages.icon_back), modifier = Modifier.size(80.dp),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onSurface)
    }
}