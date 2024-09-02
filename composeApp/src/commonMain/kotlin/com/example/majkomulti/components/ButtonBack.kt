package com.example.majkomulti.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.majkomulti.images.MajkoResourceImages
import io.github.skeptick.libres.compose.painterResource

@Composable
fun ButtonBack(onClick: ()-> Unit){
    IconButton(onClick = onClick ) {
        Icon(painter = painterResource(MajkoResourceImages.icon_back), contentDescription = "",
            tint = MaterialTheme.colorScheme.background)
    }
}