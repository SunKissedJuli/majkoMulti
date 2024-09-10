package com.example.majkomulti.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.majkomulti.components.CircleAsyncImage
import com.example.majkomulti.components.LineTextField
import com.example.majkomulti.images.MajkoResourceImages
import com.example.majkomulti.screen.RootApp.RootNavigator
import com.example.majkomulti.screen.login.LoginScreen
import com.example.majkomulti.strings.MajkoResourceStrings
import io.github.skeptick.libres.compose.painterResource
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

internal actual class ProfileScreen: Screen, KoinComponent {

    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { ProfileViewModel() }

        LaunchedEffect(Unit){
            launch {
                viewModel.loadData()
            }
        }

        val uiState by viewModel.stateFlow.collectAsState()
        val navigator =  LocalNavigator.currentOrThrow

        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            SetProfileScreen(uiState, {viewModel.updateUserName(it)}, {viewModel.updateUserEmail(it)}, viewModel)
        }

        /*if (uiState.isChangePassword){
            ChangePassword(uiState,  onUpdateOldPassword = viewModel::updateOldPassword,
                onUpdateNewPassword = viewModel::updateNewPassword,
                onUpdateConfirmPassword = viewModel::updateConfirmPassword,
                onSave = viewModel::changePassword,
                onDismissRequest = viewModel::changePasswordScreen)
        }*/
    }
}

@Composable
private fun SetProfileScreen(uiState: ProfileState, onUpdateUserName: (String) -> Unit, onUpdateUserEmail: (String) -> Unit,
                             viewModel: ProfileViewModel
) {

    val navigator = RootNavigator.currentOrThrow

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        CircleAsyncImage(uiState.avatar,200)
    }

    Spacer(modifier = Modifier.height(20.dp))
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = MajkoResourceStrings.profile_username, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(modifier = Modifier.width(20.dp))

        LineTextField(uiState.userName, {onUpdateUserName(it)},
            placeholder = "", modifier = Modifier.width(150.dp))

        IconButton(onClick = { viewModel.updateNameData(uiState.userName)}) {
            Icon(
                painterResource(MajkoResourceImages.icon_check), contentDescription = "",
                tint = MaterialTheme.colorScheme.primary)
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = MajkoResourceStrings.profile_login, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(modifier = Modifier.width(20.dp))

        LineTextField(uiState.userEmail, {onUpdateUserEmail(it)},
            placeholder = "", modifier = Modifier.width(150.dp))

        IconButton(onClick = { viewModel.updateEmailData(uiState.userName, uiState.userEmail) }) {
            Icon(painter = painterResource(MajkoResourceImages.icon_check),
                contentDescription = "", tint = MaterialTheme.colorScheme.primary)
        }
    }

    Row(
        Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom){
        BlueRoundedButton({ viewModel.changePasswordScreen() }, "Забыли пароль?",
            modifier = Modifier.padding(bottom = 10.dp, top = 20.dp))
    }

    Row(
        Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom) {

        Text(text = MajkoResourceStrings.profile_logout,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable {
                viewModel.forgetAccount()
                navigator.replaceAll(LoginScreen())
            })
    }
}


/*@Composable
private fun ChangePassword(uiState: ProfileState,onUpdateOldPassword: (String) -> Unit,
                           onUpdateNewPassword: (String) -> Unit,
                           onUpdateConfirmPassword: (String) -> Unit,
                           onSave: () -> Unit,
                           onDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(380.dp)
                .padding(16.dp)
                .clip(RoundedCornerShape(25.dp))
                .background(MaterialTheme.colorScheme.secondary)) {

            WhiteRoundedTextField(uiState.oldPassword, onUpdateOldPassword,
                stringResource(R.string.profile_oldpassword) )
            WhiteRoundedTextField(uiState.newPassword, onUpdateNewPassword,
                stringResource(R.string.profile_newpassword) )
            WhiteRoundedTextField(uiState.confirmPassword, onUpdateConfirmPassword,
                stringResource(R.string.profile_confirmpassword) )


            Row(Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically){
                BlueRoundedButton(onSave, stringResource(R.string.profile_save))
            }
        }
    }
}*/
