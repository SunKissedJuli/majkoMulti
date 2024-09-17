package com.example.majkomulti.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.example.majkomulti.ext.clickableBlank
import com.example.majkomulti.strings.MajkoResourceStrings

@Composable
fun AsyncFormatImageViewer(link: String, uriHandler: UriHandler, modifier: Modifier = Modifier){
    if(isImage(link)){
        SubcomposeAsyncImage((link),
            contentDescription = "",
            modifier.height(90.dp).clip(RoundedCornerShape(10.dp))
                .clickableBlank { uriHandler.openUri(link) },
            contentScale = ContentScale.Crop,
            error = {
                Row(modifier.height(90.dp).width(140.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(10.dp)),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically) {}
            }
        )
    }
    else{
        val fileExtension = link.substring(link.lastIndexOf("."))
        Row(modifier.height(90.dp).width(140.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(10.dp))
            .clickableBlank { uriHandler.openUri(link) },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = fileExtension, color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

fun isImage(link: String): Boolean{
    return link.endsWith(".png", ignoreCase = true) ||
            link.endsWith(".jpg", ignoreCase = true) ||
            link.endsWith(".jpeg", ignoreCase = true)
}
