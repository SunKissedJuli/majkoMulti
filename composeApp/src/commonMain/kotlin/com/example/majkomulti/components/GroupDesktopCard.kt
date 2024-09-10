package com.example.majkomulti.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.Navigator
import coil3.compose.SubcomposeAsyncImage
import com.example.majkomulti.commons.Constantas
import com.example.majkomulti.domain.modelsUI.Group.GroupUi
import com.example.majkomulti.images.MajkoResourceImages
import io.github.skeptick.libres.compose.painterResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GroupDesktopCard(
    priorityColor : Color = MaterialTheme.colorScheme.background,
    groupData: GroupUi,
    onLongTap: (String) -> Unit = {},
    onLongTapRelease: (String) -> Unit = {},
    isSelected: Boolean = false){

    val borderColor = if (isSelected) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.secondary

    Column(modifier = Modifier
        .fillMaxWidth()
        .height(150.dp)
        .padding(start = 10.dp, top = 10.dp, end = 10.dp)
        .clip(RoundedCornerShape(20.dp))
        .background(color = priorityColor)
        .border(2.dp, color = borderColor, shape = RoundedCornerShape(20.dp))
        .combinedClickable(
            onClick = { },
            onLongClick = {
                if (isSelected) {
                    onLongTapRelease(groupData.id)
                } else {
                    onLongTap(groupData.id)
                }
            },
        ),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top) {
        Row(
            Modifier
                .padding(start = 15.dp, top = 10.dp, end = 10.dp)
                .fillMaxWidth()
                .fillMaxHeight(0.27f),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically){

            CircleAsyncImage(groupData.author.image,27)
            Spacer(Modifier.width(15.dp))
            Text(text= groupData.title, modifier = Modifier.fillMaxWidth(0.7f), fontSize = 14.sp, fontWeight = FontWeight.Medium, softWrap = true, maxLines = 2)

        }
        Row(
            Modifier
                .padding(start = 15.dp, top = 10.dp, end = 10.dp)
                .fillMaxWidth()
                .fillMaxHeight(0.7f),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top){
            Text(text= groupData.description, fontSize = 13.sp, fontWeight = FontWeight.Light, softWrap = true, maxLines = 9)
        }
        Row(
            Modifier
                .fillMaxSize()
                .padding(end = 15.dp, bottom = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End) {

            Image(painter = painterResource(MajkoResourceImages.icon_members),
                contentDescription = "")
            Spacer(Modifier.width(7.dp))
            Text(text = groupData.members.size.toString())

        }
    }
}