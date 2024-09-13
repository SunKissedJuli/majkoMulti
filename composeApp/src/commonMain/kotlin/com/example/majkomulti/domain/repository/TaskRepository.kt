package com.example.majkomulti.domain.repository

import com.example.majkomulti.data.models.NoteData.NoteById
import com.example.majkomulti.data.models.NoteData.NoteData
import com.example.majkomulti.data.models.NoteData.NoteUpdate
import com.example.majkomulti.data.models.Task.SearchTask
import com.example.majkomulti.data.models.Task.TaskById
import com.example.majkomulti.data.models.Task.TaskByIdUnderscore
import com.example.majkomulti.data.models.Task.TaskData
import com.example.majkomulti.data.models.Task.TaskUpdateData
import com.example.majkomulti.domain.modelsUI.MessageDataUi
import com.example.majkomulti.domain.modelsUI.Note.NoteDataUi
import com.example.majkomulti.domain.modelsUI.Task.TaskDataUi
import com.example.majkomulti.platform.Either
import com.example.majkomulti.platform.Failure
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun getAllUserTask(search: SearchTask): Either<Failure, List<TaskDataUi>>

    suspend fun getUserTask(search: SearchTask): Either<Failure, List<TaskDataUi>>

    suspend fun postNewTask(task: TaskData): Either<Failure, TaskDataUi>

    suspend fun getTaskById(taskId: TaskById): Either<Failure, TaskDataUi>

    suspend fun removeTask(taskId: TaskByIdUnderscore): Either<Failure, Unit>

    suspend fun updateTask(taskData: TaskUpdateData):Either<Failure, TaskDataUi>

    suspend fun getSubtask(taskId: TaskById): Either<Failure, List<TaskDataUi>>

    //фавориты
    suspend fun removeFavotire(taskId: TaskById): Either<Failure, MessageDataUi>

    suspend fun addToFavorite(taskId: TaskById): Either<Failure, MessageDataUi>

    suspend fun getAllFavorites(): Either<Failure, List<TaskDataUi>>

    //записи
    suspend fun addNote(note: NoteData) : Either<Failure, NoteDataUi>

    suspend fun updateNote(note: NoteUpdate) : Either<Failure, NoteDataUi>

    suspend fun removeNote(noteId: NoteById) : Either<Failure, Unit>

    suspend fun getNotes(taskId: TaskById) : Either<Failure, List<NoteDataUi>>
}