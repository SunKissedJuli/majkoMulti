package com.example.majkomulti.screen.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.majkomulti.screen.MainTab.MainTabScreen
import com.example.majkomulti.screen.register.RegistrationScreen
import com.example.majkomulti.strings.MajkoResourceStrings
import kotlinx.coroutines.launch

internal actual class SplashScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { SplashViewModel(Unit) }
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
                            navigator.replaceAll(MainTabScreen())
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
                CircularProgressIndicator(
                    modifier = Modifier.size(100.dp),
                    strokeWidth = 8.dp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}