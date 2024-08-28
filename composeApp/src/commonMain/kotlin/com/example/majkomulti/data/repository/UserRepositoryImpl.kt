package com.example.majkomulti.data.repository

import com.example.majkomulti.data.api.MajkoApi
import  com.example.majkomulti.data.mapper.toUI
import  com.example.majkomulti.data.mapper.toUI
import com.example.majkomulti.data.models.User.UserUpdateEmail
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
import com.example.majkomulti.platform.apiCall

class UserRepositoryImpl (private val majkoApi: MajkoApi): UserRepository {

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
}

