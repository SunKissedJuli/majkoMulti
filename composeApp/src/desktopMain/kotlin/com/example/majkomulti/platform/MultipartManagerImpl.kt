package com.example.majkomulti.platform

import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.http.content.PartData
import kotlin.test.todo

actual class MultipartManagerImpl : MultipartManager {

    actual override suspend fun createMultipart(files: List<Form>): MultiPartFormDataContent {
        return MultiPartFormDataContent(emptyList())
    }

    actual override suspend fun createFormData(files: List<Form>): List<PartData>{
        return emptyList()
    }

}