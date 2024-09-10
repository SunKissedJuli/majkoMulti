package com.example.majkomulti.data.repository

import com.example.majkomulti.data.api.MajkoApi
import  com.example.majkomulti.data.mapper.toUI
import  com.example.majkomulti.data.mapper.toUI
import com.example.majkomulti.data.models.User.UserUpdateEmail
import com.example.majkomulti.data.models.User.UserUpdateImage
import com.example.majkomulti.data.models.User.UserUpdateName
import com.example.majkomulti.data.models.User.UserUpdatePassword
import com.example.majkomulti.data.models.UserSignIn.UserSignInData
import com.example.majkomulti.data.models.UsrtSignUp.UserSignUpData
import com.example.majkomulti.domain.modelsUI.User.CurrentUserDataUi
import com.example.majkomulti.domain.modelsUI.UserLogin.UserSignInDataUi
import com.example.majkomulti.domain.modelsUI.UserLogin.UserSignUpDataUi
import com.example.majkomulti.domain.repository.UserRepository
import com.example.majkomulti.platform.Either
import com.example.majkomulti.platform.Failure
import com.example.majkomulti.platform.Form
import com.example.majkomulti.platform.MultipartManager
import com.example.majkomulti.platform.apiCall
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class UserRepositoryImpl (private val majkoApi: MajkoApi, private val multipartManager: MultipartManager): UserRepository {

    override suspend fun signIn(user: UserSignInData): Either<Failure, UserSignInDataUi> {
        return apiCall(call = {
            majkoApi.signIn(user)
        },
            mapResponse = { loginData  -> loginData.toUI() })
    }

    override suspend fun signUp(user: UserSignUpData): Either<Failure, UserSignUpDataUi> {
        return apiCall(call = {
            majkoApi.signUp(user)
        },
            mapResponse = { loginData  -> loginData.toUI() })
    }

    override suspend fun currentUser(): Either<Failure, CurrentUserDataUi> {
        return apiCall (call = {
            majkoApi.currentUser()
        },
            mapResponse = { userData -> userData.toUI() })
    }

    override suspend fun updateUserName(user: UserUpdateName): Either<Failure, CurrentUserDataUi> {
        return apiCall (call = {
            majkoApi.updateUserName(user)
        },
            mapResponse = { userData -> userData.toUI() })
    }

    override suspend fun updateUserEmail(user: UserUpdateEmail): Either<Failure, CurrentUserDataUi> {
        return apiCall (call = {
            majkoApi.updateUserEmail(user)
        },
            mapResponse = { userData -> userData.toUI() })
    }

    override suspend fun updateUserPassword(user: UserUpdatePassword): Either<Failure, CurrentUserDataUi> {
        return apiCall (call = {
            majkoApi.updateUserPassword(user)
        },
            mapResponse = { userData -> userData.toUI() })
    }

    override suspend fun updateUserImage(user: String, image: String): Either<Failure, CurrentUserDataUi> {
        return apiCall(call = {
            val list =  buildList<Form> {
                add(Form.FormBody("name", user))
                add(Form.FormFile("image", image))
            }

            val body = multipartManager.createMultipart(list)
            majkoApi.updateUserImage(body)
        }, mapResponse = { userData -> userData.toUI() })
    }
}

