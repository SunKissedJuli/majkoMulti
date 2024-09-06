package com.example.majkomulti.data.repository

import com.example.majkomulti.data.api.MajkoApi
import com.example.majkomulti.data.mapper.toUI
import com.example.majkomulti.domain.modelsUI.InfoUi
import com.example.majkomulti.domain.repository.InfoRepository
import com.example.majkomulti.platform.Either
import com.example.majkomulti.platform.Failure
import com.example.majkomulti.platform.apiCall
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


class InfoRepositoryImpl (private val majkoApi: MajkoApi): InfoRepository {

    override suspend fun getStatuses(): Either<Failure, List<InfoUi>> {
        return apiCall (call = {
            majkoApi.getStatuses()
        },
            mapResponse = { info -> info.toUI() })
    }

    override suspend fun getPriorities(): Either<Failure, List<InfoUi>> {
        return apiCall (call = {
            majkoApi.getPriorities()
        },
            mapResponse = { info -> info.toUI() })
    }

    override suspend fun uploadFile(taskId: String, files: File): Either<Failure, Unit> {
        // Проверка существования и доступности файла
        if (!files.exists() || !files.canRead()) {
            return Either.Left(Failure.Http(422,"File not here"))
        }

        // Проверка на пустой файл
        if (files.length() == 0L) {
            return Either.Left(Failure.Http(422,"File empty"))
        }

        // Создание MultipartBody.Part для файла
        val filePart = MultipartBody.Part.createFormData("files", files.name,
            files.asRequestBody("application/octet-stream".toMediaTypeOrNull()))

        println("!!!!!!!!!!!!!!!!!!!сам файл: ${files.name}, путь: ${files.absolutePath}")

        return apiCall(call = {
            majkoApi.uploadFile(taskId, filePart)
        }, mapResponse = { Unit })
    }

}