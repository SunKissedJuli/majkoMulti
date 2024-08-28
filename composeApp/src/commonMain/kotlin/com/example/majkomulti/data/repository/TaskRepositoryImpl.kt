package com.example.majkomulti.data.repository

import com.example.majkomulti.data.api.MajkoApi
import com.example.majkomulti.data.mapper.toUI
import com.example.majkomulti.data.models.Task.SearchTask
import com.example.majkomulti.domain.modelsUI.Task.TaskDataUi
import com.example.majkomulti.domain.repository.TaskRepository
import com.example.majkomulti.platform.Either
import com.example.majkomulti.platform.Failure
import com.example.majkomulti.platform.apiCall

class TaskRepositoryImpl (private val majkoApi: MajkoApi): TaskRepository {

    override suspend fun getAllUserTask(search: SearchTask): Either<Failure, List<TaskDataUi>> {
        return apiCall (call = {
            majkoApi.getAllUserTask(search)
        },
            mapResponse = { userData -> userData.toUI() })
    }

    override suspend fun getAllFavorites(): Either<Failure, List<TaskDataUi>> {
        return apiCall (call = {
            majkoApi.getAllFavorites()
        },
            mapResponse = { userData -> userData.toUI() })
    }
}