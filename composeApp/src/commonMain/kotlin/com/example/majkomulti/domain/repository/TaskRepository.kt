package com.example.majkomulti.domain.repository

import com.example.majkomulti.data.models.Task.SearchTask
import com.example.majkomulti.domain.modelsUI.Task.TaskDataUi
import com.example.majkomulti.platform.Either
import com.example.majkomulti.platform.Failure
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun getAllUserTask(search: SearchTask): Either<Failure, List<TaskDataUi>>

 //   fun postNewTask(task: TaskData): TaskDataUi

 //   fun getTaskById(taskId: TaskById): Flow<ApiResult<TaskDataResponseUi>>

 //   fun removeTask(taskId: TaskByIdUnderscore): Flow<ApiResult<Unit>>

 //   fun updateTask(taskData: TaskUpdateData): Flow<ApiResult<TaskDataResponseUi>>

 //   fun getSubtask(taskId: TaskById): Flow<ApiResult<List<TaskDataResponseUi>>>

    //фавориты
   // fun removeFavotire(taskId: TaskById): Flow<ApiResult<MessageDataUi>>

 //   fun addToFavorite(taskId: TaskById): Flow<ApiResult<MessageDataUi>>

    suspend fun getAllFavorites(): Either<Failure, List<TaskDataUi>>

    //записи
 //   fun addNote(note: NoteData) : Flow<ApiResult<NoteDataResponseUi>>

  //  fun updateNote(note: NoteUpdate) : Flow<ApiResult<NoteDataResponseUi>>

  //  fun removeNote(noteId: NoteById) : Flow<ApiResult<Unit>>

  //  fun getNotes(taskId: TaskById) : Flow<ApiResult<List<NoteDataResponseUi>>>
}