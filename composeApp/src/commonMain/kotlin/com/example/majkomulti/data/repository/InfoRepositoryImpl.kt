package com.example.majkomulti.data.repository

import com.example.majkomulti.data.api.MajkoApi
import com.example.majkomulti.data.mapper.toUI
import com.example.majkomulti.data.models.MessageData
import com.example.majkomulti.domain.modelsUI.InfoUi
import com.example.majkomulti.domain.modelsUI.MessageDataUi
import com.example.majkomulti.domain.repository.InfoRepository
import com.example.majkomulti.platform.Either
import com.example.majkomulti.platform.Failure
import com.example.majkomulti.platform.Form
import com.example.majkomulti.platform.MultipartManager
import com.example.majkomulti.platform.apiCall
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


class InfoRepositoryImpl (private val majkoApi: MajkoApi, private val multipartManager: MultipartManager): InfoRepository {

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

    override suspend fun uploadFile(taskId: String, files: String): Either<Failure, MessageDataUi> {
        return apiCall(call = {
            val body = multipartManager.createMultipart(
                listOf(
                    Form.FormBody("task_id", taskId),
                    Form.FormFile("files", files),
                )  )

            majkoApi.uploadFile(body)
        }, mapResponse = { MessageData ->MessageData.toUI() })

    }

}