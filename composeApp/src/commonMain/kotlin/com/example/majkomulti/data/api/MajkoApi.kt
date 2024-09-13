package com.example.majkomulti.data.api

import com.example.majkomulti.data.models.GroupData.GroupById
import com.example.majkomulti.data.models.GroupData.GroupByIdUnderscore
import com.example.majkomulti.data.models.GroupData.GroupData
import com.example.majkomulti.data.models.GroupData.GroupInviteResponse
import com.example.majkomulti.data.models.GroupData.GroupResponse
import com.example.majkomulti.data.models.GroupData.GroupUpdate
import com.example.majkomulti.data.models.GroupData.ProjectInGroup
import com.example.majkomulti.data.models.Info
import com.example.majkomulti.data.models.MessageData
import com.example.majkomulti.data.models.NoteData.NoteById
import com.example.majkomulti.data.models.NoteData.NoteData
import com.example.majkomulti.data.models.NoteData.NoteDataResponse
import com.example.majkomulti.data.models.NoteData.NoteUpdate
import com.example.majkomulti.data.models.ProjectData.JoinByInviteProjectData
import com.example.majkomulti.data.models.ProjectData.ProjectById
import com.example.majkomulti.data.models.ProjectData.ProjectByIdUnderscore
import com.example.majkomulti.data.models.ProjectData.ProjectCreateInviteResponse
import com.example.majkomulti.data.models.ProjectData.ProjectCurrentResponse
import com.example.majkomulti.data.models.ProjectData.ProjectData
import com.example.majkomulti.data.models.ProjectData.ProjectDataResponse
import com.example.majkomulti.data.models.ProjectData.ProjectUpdate
import com.example.majkomulti.data.models.Task.SearchTask
import com.example.majkomulti.data.models.Task.TaskById
import com.example.majkomulti.data.models.Task.TaskByIdUnderscore
import com.example.majkomulti.data.models.Task.TaskData
import com.example.majkomulti.data.models.Task.TaskDataResponse
import com.example.majkomulti.data.models.Task.TaskUpdateData
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
import de.jensklingenberg.ktorfit.http.HTTP
import de.jensklingenberg.ktorfit.http.Multipart
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Part
import io.ktor.client.request.forms.MultiPartFormDataContent
import okhttp3.MultipartBody

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

  /*  @Multipart
    @POST("api/user/update")
    suspend fun updateUserImage(@Part("name") name: String, @Part("image") image : MultipartBody.Part): CurrentUserDataResponse
*/
    @Multipart
    @POST("api/user/update")
    suspend fun updateUserImage(@Body body: MultiPartFormDataContent): CurrentUserDataResponse


    //таски
    @POST("api/task/allUserTasks")
    suspend fun getAllUserTask(@Body search: SearchTask): List<TaskDataResponse>

    @POST("api/task/getPersonal")
    suspend fun getPersonalUserTask(@Body search: SearchTask): List<TaskDataResponse>

    @POST("api/task/getPrivate")
    suspend fun getGroupUserTask(@Body search: SearchTask): List<TaskDataResponse>

    @POST("api/task/create")
    suspend fun postNewTask(@Body task: TaskData): TaskDataResponse

    @POST("api/task/getById")
    suspend fun getTaskById(@Body taskId: TaskById): TaskDataResponse

    @HTTP(method = "DELETE", path = "api/task/delete", hasBody = true)
    suspend fun removeTask(@Body taskId: TaskByIdUnderscore) :Unit

    @POST("api/task/update")
    suspend fun updateTask(@Body taskData: TaskUpdateData) : TaskDataResponse

    @POST("api/task/getSubtaskForTask")
    suspend fun getSubtask(@Body taskId: TaskById): List<TaskDataResponse>

    //фавориты
    @HTTP(method = "DELETE", path = "api/task/removeFavorite", hasBody = true)
    suspend fun removeFavotire(@Body taskId: TaskById) : MessageData

    @POST("api/task/addToFavorite")
    suspend fun addToFavorite(@Body taskId: TaskById) : MessageData

    @GET("api/task/favorites")
    suspend fun getAllFavorites(): List<TaskDataResponse>

    //проекты
    @POST("api/project/getPersonal")
    suspend fun getPersonalProject(@Body search: SearchTask): List<ProjectDataResponse>

    @POST("api/project/getPrivate")
    suspend fun getGroupProject(@Body search: SearchTask): List<ProjectDataResponse>

    @POST("api/project/create")
    suspend fun postNewProject(@Body project: ProjectData): ProjectDataResponse

    @POST("api/project/getById")
    suspend fun getProjectById(@Body projectById: ProjectById) : ProjectCurrentResponse

    @POST("api/project/update")
    suspend fun updateProject(@Body project: ProjectUpdate) : ProjectCurrentResponse

    @HTTP(method = "DELETE", path = "api/project/delete", hasBody = true)
    suspend fun removeProject(@Body projectId: ProjectById) : Unit

    @POST("api/project/createInvite")
    suspend fun createInvitetoProject(@Body projectById: ProjectByIdUnderscore) : ProjectCreateInviteResponse

    @POST("api/project/joinByInvitation")
    suspend fun joinByInvitation(@Body invite: JoinByInviteProjectData) : MessageData


    //записи
    @POST("api/note/create")
    suspend fun addNote(@Body note: NoteData) : NoteDataResponse

    @POST("api/note/update")
    suspend fun updateNote(@Body note: NoteUpdate) : NoteDataResponse

    @HTTP(method = "DELETE", path = "api/note/delete", hasBody = true)
    suspend fun removeNote(@Body noteId: NoteById) : Unit

    @POST("api/note/getNotes?aseae=asdsad")
    suspend fun getNotes(@Body taskId: TaskById) : List<NoteDataResponse>


    //группы
    @POST("api/group/create")
    suspend fun addGroup(@Body group: GroupData) : GroupResponse

    @POST("api/group/getPersonal")
    suspend fun getPersonalGroup(@Body search: SearchTask): List<GroupResponse>

    @POST("api/group/getPrivate")
    suspend fun getGroupGroup(@Body search: SearchTask): List<GroupResponse>

    @POST("api/group/getById")
    suspend fun getGroupById(@Body groupId: GroupById): GroupResponse

    @POST("api/group/update")
    suspend fun updateGroup(@Body group: GroupUpdate): GroupResponse

    @HTTP(method = "DELETE", path = "api/group/delete", hasBody = true)
    suspend fun removeGroup(@Body groupId: GroupById) : Unit

    @POST("api/group/addProjectInGroup")
    suspend fun addProjectInGroup(@Body group: ProjectInGroup): ProjectDataResponse

    @POST("api/group/createInvite")
    suspend fun createInvitetoGroup(@Body groupId: GroupByIdUnderscore) : GroupInviteResponse

    @POST("api/group/joinByInvitation")
    suspend fun joinGroupByInvitation(@Body invite: JoinByInviteProjectData) : MessageData



    //инфо
    @GET("api/get_statuses")
    suspend fun getStatuses() : List<Info>

    @GET("api/get_priorities")
    suspend fun getPriorities() : List<Info>

  //  @Multipart
   // @POST("api/file/upload")
  //  suspend fun uploadFile(@Part("task_id") taskId: String, @Part("files") files: MultipartBody.Part) : Unit

    @Multipart
    @POST("api/file/upload")
    suspend fun uploadFile(@Body body: MultiPartFormDataContent): MessageData

}