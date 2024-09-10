package com.example.majkomulti.screen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.majkomulti.components.BlueRoundedButton
import com.example.majkomulti.components.LineTextField
import com.example.majkomulti.screen.register.RegistrationScreen
import com.example.majkomulti.screen.splash.SplashScreen
import com.example.majkomulti.strings.MajkoResourceStrings
import kotlinx.coroutines.launch


internal actual class LoginScreen: Screen {

    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { LoginViewModel() }
        val uiState by viewModel.stateFlow.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(viewModel) {
            launch {
                viewModel.container.sideEffectFlow.collect() {
                    when (it) {
                        is LoginEvent.LoginSuccess -> {
                            navigator.push(SplashScreen())
                        }
                    }
                }
            }
        }

        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Column(
                Modifier
                    .fillMaxHeight(0.3f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Row() {
                    Spacer(modifier = Modifier.width(60.dp))
                    Text(
                        text = MajkoResourceStrings.app_name,
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row() {
                    Spacer(modifier = Modifier.width(25.dp))
                    Text(
                        text = MajkoResourceStrings.login_welcomtext,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Column(
                Modifier
                    .fillMaxSize()
                    .background(
                        color = MaterialTheme.colorScheme.secondary,
                        shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(1.dp))
                Column(
                    Modifier
                        .fillMaxHeight(0.55f)
                        .fillMaxWidth(0.8f)
                        .padding(bottom = 60.dp)
                        .background(
                            color = MaterialTheme.colorScheme.background,
                            shape = RoundedCornerShape(30.dp)
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {

                        LineTextField(
                            uiState.login, { viewModel.updateLogin(it) },
                            placeholder = MajkoResourceStrings.login_login
                        )

                        LineTextField(
                            uiState.password, { viewModel.updatePassword(it) },
                            placeholder = MajkoResourceStrings.login_password
                        )

                    }
                }
            }
        }

        Column(
            Modifier
                .fillMaxSize()
                .padding(bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {

            BlueRoundedButton(
                { viewModel.login(uiState.login, uiState.password) },
                MajkoResourceStrings.login_enter,
                modifier = Modifier.fillMaxWidth(0.73f),
                rounded = 15
            )

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = MajkoResourceStrings.login_registrationoffer,
                modifier = Modifier.clickable { navigator.push(RegistrationScreen()) })
        }
    }
}