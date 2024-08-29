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
import coil3.compose.AsyncImage
import com.example.majkomulti.commons.Constantas
import com.example.majkomulti.domain.modelsUI.Project.ProjectDataUi
import com.example.majkomulti.images.MajkoResourceImages
import io.github.skeptick.libres.compose.painterResource
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProjectDesktopCard(
    navigator: Navigator,
    priorityColor: Color = MaterialTheme.colorScheme.background,
    projectData: ProjectDataUi,
    onLongTap: (String) -> Unit = {},
    onLongTapRelease: (String) -> Unit = {},
    isSelected: Boolean = false
) {
    val borderColor = if (isSelected) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.secondary

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(start = 10.dp, top = 10.dp, end = 10.dp)
            .clip(RoundedCornerShape(20.dp))
            .border(
                2.dp,
                color = borderColor,
                shape = RoundedCornerShape(20.dp)
            )
            .background(color = priorityColor)
            .combinedClickable(
                onClick = {  },
                onLongClick = {
                    if (isSelected) {
                        onLongTapRelease(projectData.id)
                    } else {
                        onLongTap(projectData.id)
                    }
                },
            ),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            Modifier
                .padding(start = 15.dp, top = 10.dp, end = 10.dp)
                .fillMaxWidth()
                .fillMaxHeight(0.27f),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if(!projectData.author.image.isNullOrEmpty()){
                AsyncImage((Constantas.BASE_URL + projectData.author.image),
                    contentDescription = "",
                    Modifier
                        .size(25.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop)
            }else{
                Box(
                    Modifier
                        .fillMaxHeight(0.8f)
                        .size(25.dp)
                        .aspectRatio(1f)
                        .background(MaterialTheme.colorScheme.primary, shape = CircleShape))
            }
            Spacer(Modifier.width(15.dp))
            Text(
                text = projectData.name,
                modifier = Modifier.fillMaxWidth(0.7f),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                softWrap = true,
                maxLines = 2
            )
        }
        Row(
            Modifier
                .padding(start = 15.dp, top = 10.dp, end = 10.dp)
                .fillMaxWidth()
                .fillMaxHeight(0.7f),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = projectData.description,
                fontSize = 13.sp,
                fontWeight = FontWeight.Light,
                softWrap = true,
                maxLines = 4
            )
        }
        Row(
            Modifier
                .fillMaxSize()
                .padding(end = 15.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(Modifier.padding(start = 15.dp)){
                val formatter = DateTimeFormatter.ISO_DATE_TIME
                val dateTime = LocalDateTime.parse(projectData.createdAt, formatter)
                Text(text= dateTime.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale("ru"))
                        + ", " + dateTime.dayOfMonth + " " + dateTime.month.getDisplayName(TextStyle.FULL, Locale("ru")),
                    fontSize = 13.sp, fontWeight = FontWeight.Medium)
            }
            if (!projectData.isPersonal) {
                Row {
                    Image(painterResource(MajkoResourceImages.icon_members), contentDescription = "")
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(text = projectData.members.size.toString())
                }

            }
        }
    }
}