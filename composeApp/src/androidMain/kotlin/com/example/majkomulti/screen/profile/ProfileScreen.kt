package com.example.majkomulti.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.SubcomposeAsyncImage
import com.example.majkomulti.ext.clickableBlank
import com.example.majkomulti.components.BlueRoundedButton
import com.example.majkomulti.components.CustomScaffold
import com.example.majkomulti.components.LineTextField
import com.example.majkomulti.ext.granted
import com.example.majkomulti.images.MajkoResourceImages
import com.example.majkomulti.platform.ImagePicker
import com.example.majkomulti.platform.permission.model.Permission
import com.example.majkomulti.platform.permission.service.PermissionsService
import com.example.majkomulti.screen.RootApp.RootNavigator
import com.example.majkomulti.screen.login.LoginScreen
import com.example.majkomulti.strings.MajkoResourceStrings
import io.github.skeptick.libres.compose.painterResource
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal actual class ProfileScreen: Screen, KoinComponent {

    @Composable
    override fun Content() {
        val viewModel = rememberScreenModel { ProfileViewModel() }

        LaunchedEffect(Unit){
            viewModel.loadData()
        }

        val uiState by viewModel.stateFlow.collectAsState()
        val navigator = RootNavigator.currentOrThrow
        CustomScaffold {
            Column(
                Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Column(
                    Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    CircleAsyncImageLoader(uiState.avatar, 200, ){
                        viewModel.updateUserProfile(it)
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = MajkoResourceStrings.profile_username, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Spacer(modifier = Modifier.width(20.dp))

                    LineTextField(uiState.userName, {viewModel.updateUserName(it)},
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

                    LineTextField(uiState.userEmail, {viewModel.updateUserEmail(it)},
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

                    Text(text =MajkoResourceStrings.profile_logout,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable {
                            viewModel.forgetAccount()
                            navigator.replaceAll(LoginScreen())
                        })
                }
            }
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

@Composable
fun KoinComponent.CircleAsyncImageLoader(
    image: Any?,
    size: Int = 200,
    errorColor: Color = MaterialTheme.colorScheme.primary,
    enable: Boolean = true,
    onClick:(String)->Unit = {}){

    val imagePermissionsService: PermissionsService by inject()
    var imagePickerShow by remember { mutableStateOf(false) }
    var imagePickerPermission by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    imagePermissionsService.checkPermissionFlow(Permission.READ_EXTERNAL_STORAGE)
        .collectAsState(imagePermissionsService.checkPermission(Permission.READ_EXTERNAL_STORAGE))
        .granted {
            if (imagePickerPermission) {
                imagePickerShow = true
            }
        }

    Box(modifier = Modifier.clickableBlank(enable) {

        scope.launch {
            if (imagePermissionsService.checkPermission(Permission.READ_EXTERNAL_STORAGE)
                    .granted()
            ) {
                imagePickerShow = true
            } else {
                imagePickerPermission = true
                imagePermissionsService.providePermission(Permission.READ_EXTERNAL_STORAGE)
            }

        }
    }) {
        SubcomposeAsyncImage((image),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(size.dp)
                .clip(CircleShape),
            loading = {
                Box(modifier = Modifier
                    .clickable { }
                    .size(size.dp)
                    .clip(CircleShape)
                    .background(errorColor))
            },
            error = {
                Box(modifier = Modifier
                    .clickable { }
                    .size(size.dp)
                    .clip(CircleShape)
                    .background(errorColor))
            })
    }

    ImagePicker(imagePickerShow) { image ->
        imagePickerPermission = false
        imagePickerShow = false
        val pathChosen = image ?: return@ImagePicker
        onClick(pathChosen.path)
    }
}