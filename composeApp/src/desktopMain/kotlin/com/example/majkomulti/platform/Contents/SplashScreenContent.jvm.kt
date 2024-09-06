package com.example.majkomulti.platform.Contents

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.majkomulti.screen.register.RegistrationScreen
import com.example.majkomulti.screen.splash.SplashEvent
import com.example.majkomulti.screen.splash.SplashViewModel
import com.example.majkomulti.strings.MajkoResourceStrings
import kotlinx.coroutines.launch
import com.example.majkomulti.screen.MainVerticalTab.MainVerticalTabScreen

@Composable
internal actual fun SplashScreenContent(viewModel: SplashViewModel) {
    val navigator = LocalNavigator.currentOrThrow
    LaunchedEffect(Unit){
        launch {
            viewModel.isAutorize()
        }
    }

    LaunchedEffect(viewModel) {
        launch {
            viewModel.container.sideEffectFlow.collect() {
                when (it) {
                    is SplashEvent.UserAutorize -> {
                        navigator.replaceAll(MainVerticalTabScreen())
                    }
                    is SplashEvent.UserNotAutorize ->{
                        navigator.push(RegistrationScreen())
                    }
                }
            }
        }
    }

    MaterialTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                MajkoResourceStrings.app_name,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(40.dp))
            LoadingAnimation()
        }
    }
}

@Composable
private fun LoadingAnimation() {
    val color = MaterialTheme.colorScheme.primary
    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1000, easing = LinearEasing),
            RepeatMode.Restart
        )
    )

    Box(Modifier.wrapContentSize()) {
        Canvas(modifier = Modifier.size(100.dp)) {
            drawArc(
                color = color,
                startAngle = angle,
                sweepAngle = 270f,
                useCenter = false,
                style = Stroke(width = 8.dp.toPx())
            )
        }
    }
}

