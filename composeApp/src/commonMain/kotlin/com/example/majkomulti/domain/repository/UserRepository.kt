package com.example.majkomulti.domain.repository

import com.example.majkomulti.data.models.User.UserUpdateEmail
import com.example.majkomulti.data.models.User.UserUpdateName
import com.example.majkomulti.data.models.User.UserUpdatePassword
import com.example.majkomulti.data.models.UserSignIn.UserSignInData
import com.example.majkomulti.data.models.UsrtSignUp.UserSignUpData
import com.example.majkomulti.domain.modelsUI.User.CurrentUserDataUi
import com.example.majkomulti.domain.modelsUI.UserLogin.UserSignInDataUi
import com.example.majkomulti.domain.modelsUI.UserLogin.UserSignUpDataUi
import com.example.majkomulti.platform.Either
import com.example.majkomulti.platform.Failure
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun signIn(user: UserSignInData): Either<Failure, UserSignInDataUi>

    suspend fun signUp(user: UserSignUpData): Either<Failure, UserSignUpDataUi>

    suspend fun currentUser(): Either<Failure, CurrentUserDataUi>

    suspend fun updateUserName(user: UserUpdateName): Either<Failure, CurrentUserDataUi>

    suspend fun updateUserEmail(user: UserUpdateEmail): Either<Failure, CurrentUserDataUi>

    suspend fun updateUserPassword(user: UserUpdatePassword): Either<Failure, CurrentUserDataUi>

}