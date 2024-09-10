package com.example.majkomulti.platform

import com.example.majkomulti.ext.formData
import com.example.majkomulti.ext.multipart

import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.http.content.PartData

expect class MultipartManagerImpl() : MultipartManager {

    override suspend fun createMultipart(files: List<Form>): MultiPartFormDataContent

    override suspend fun createFormData(files: List<Form>): List<PartData>

}