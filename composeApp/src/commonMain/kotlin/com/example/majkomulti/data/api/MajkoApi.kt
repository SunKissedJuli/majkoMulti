package com.example.majkomulti.data.api

import com.example.majkomulti.data.models.Info
import com.example.majkomulti.data.models.Task.SearchTask
import com.example.majkomulti.data.models.Task.TaskDataResponse
import com.example.majkomulti.data.models.User.CurrentUserDataResponse
import com.example.majkomulti.data.models.User.UserUpdateEmail
import com.example.majkomulti.data.models.User.UserUpdateName
import com.example.majkomulti.data.models.User.UserUpdatePassword
import com.example.majkomulti.data.models.UserSignIn.UserSignInData
import com.example.majkomulti.data.models.UserSignIn.UserSignInDataResponse
import com.example.majkomulti.data.models.UsrtSignUp.UserSignUpData
import com.example.majkomulti.data.models.UsrtSignUp.UserSignUpDataResponse
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Multipart
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Part

interface MajkoApi {
    //юзер
    @POST("api/auth/local/signin")
    suspend fun signIn(@Body user: UserSignInData): UserSignInDataResponse

    @POST("api/auth/local/signup")
    suspend fun signUp(@Body user: UserSignUpData): UserSignUpDataResponse

    @GET("api/user/current")
    suspend fun currentUser(): CurrentUserDataResponse

    @POST("api/user/update")
    suspend fun updateUserName(@Body user: UserUpdateName): CurrentUserDataResponse

    @POST("api/user/update")
    suspend fun updateUserEmail(@Body user: UserUpdateEmail): CurrentUserDataResponse

    @POST("api/user/update")
    suspend fun updateUserPassword(@Body user: UserUpdatePassword): CurrentUserDataResponse


    //таски
    @POST("api/task/allUserTasks")
    suspend fun getAllUserTask(@Body search: SearchTask): List<TaskDataResponse>

    //фавориты
    @GET("api/task/favorites")
    suspend fun getAllFavorites(): List<TaskDataResponse>

    //инфо
    @GET("api/get_statuses")
    suspend fun getStatuses() : List<Info>

    @GET("api/get_priorities")
    suspend fun getPriorities() : List<Info>


}