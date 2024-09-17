package com.example.majkomulti.data.repository

import com.example.majkomulti.data.api.MajkoApi
import com.example.majkomulti.data.mapper.toUI
import com.example.majkomulti.data.models.NoteData.NoteById
import com.example.majkomulti.data.models.NoteData.NoteData
import com.example.majkomulti.data.models.NoteData.NoteUpdate
import com.example.majkomulti.data.models.Task.SearchTask
import com.example.majkomulti.data.models.Task.TaskById
import com.example.majkomulti.data.models.Task.TaskByIdUnderscore
import com.example.majkomulti.data.models.Task.TaskData
import com.example.majkomulti.data.models.Task.TaskUpdateData
import com.example.majkomulti.domain.manager.AuthManager
import com.example.majkomulti.domain.modelsUI.MessageDataUi
import com.example.majkomulti.domain.modelsUI.Note.NoteDataUi
import com.example.majkomulti.domain.modelsUI.Task.TaskDataUi
import com.example.majkomulti.domain.repository.TaskRepository
import com.example.majkomulti.platform.Either
import com.example.majkomulti.platform.Failure
import com.example.majkomulti.platform.apiCall

class TaskRepositoryImpl (private val majkoApi: MajkoApi, private val manager: AuthManager): TaskRepository {

    override suspend fun getAllUserTask(search: SearchTask): Either<Failure, List<TaskDataUi>> {

        return apiCall (call = {
            majkoApi.getAllUserTask(search)
        },
            mapResponse = { userData -> userData.toUI() })
    }

    override suspend fun getUserTask(search: SearchTask): Either<Failure, List<TaskDataUi>> {
        if(manager.isPrivate){
            return apiCall (call = {
                majkoApi.getPersonalUserTask(search)
            },
                mapResponse = { userData -> userData.toUI() })
        }else{
            return apiCall (call = {
                majkoApi.getGroupUserTask(search)
            },
                mapResponse = { userData -> userData.toUI() })
        }

    }

    override suspend fun postNewTask(task: TaskData): Either<Failure, TaskDataUi> {
        return apiCall (call = {
            majkoApi.postNewTask(task)
        },
            mapResponse = { userData -> userData.toUI() })
    }

    override suspend fun getTaskById(taskId: TaskById): Either<Failure, TaskDataUi> {
        return apiCall (call = {
            majkoApi.getTaskById(taskId)
        },
            mapResponse = { userData -> userData.toUI() })
    }

    override suspend fun removeTask(taskId: TaskByIdUnderscore): Either<Failure, Unit> {
        return apiCall (call = {
            majkoApi.removeTask(taskId)
        })
    }

    override suspend fun updateTask(taskData: TaskUpdateData): Either<Failure, TaskDataUi> {
        return apiCall (call = {
            majkoApi.updateTask(taskData)
        },
            mapResponse = { userData -> userData.toUI() })
    }

    override suspend fun getSubtask(taskId: TaskById): Either<Failure, List<TaskDataUi>> {
        return apiCall (call = {
            majkoApi.getSubtask(taskId)
        },
            mapResponse = { userData -> userData.toUI() })
    }

    override suspend fun removeFavotire(taskId: TaskById): Either<Failure, MessageDataUi> {
        return apiCall (call = {
            majkoApi.removeFavotire(taskId)
        },
            mapResponse = { userData -> userData.toUI() })
    }

    override suspend fun addToFavorite(taskId: TaskById): Either<Failure, MessageDataUi> {
        return apiCall (call = {
            majkoApi.addToFavorite(taskId)
        },
            mapResponse = { userData -> userData.toUI() })
    }

    override suspend fun getAllFavorites(): Either<Failure, List<TaskDataUi>> {
        return apiCall (call = {
            majkoApi.getAllFavorites()
        },
            mapResponse = { userData -> userData.toUI() })
    }

    override suspend fun addNote(note: NoteData): Either<Failure, NoteDataUi> {
        return apiCall (call = {
            majkoApi.addNote(note)
        },
            mapResponse = { userData -> userData.toUI() })
    }

    override suspend fun updateNote(note: NoteUpdate): Either<Failure, NoteDataUi> {
        return apiCall (call = {
            majkoApi.updateNote(note)
        },
            mapResponse = { userData -> userData.toUI() })
    }

    override suspend fun removeNote(noteId: NoteById): Either<Failure, Unit> {
        return apiCall (call = {
            majkoApi.removeNote(noteId)
        })
    }

    override suspend fun getNotes(taskId: TaskById): Either<Failure, List<NoteDataUi>> {
        return apiCall (call = {
            majkoApi.getNotes(taskId)
        },
            mapResponse = { userData -> userData.toUI() })
    }
}