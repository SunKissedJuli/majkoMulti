package com.example.majkomulti.platform

import android.content.Context
import com.example.majkomulti.ext.file
import com.example.majkomulti.ext.formData
import com.example.majkomulti.ext.multipart

import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.http.content.PartData
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

actual class MultipartManagerImpl : MultipartManager, KoinComponent {

    val context: Context by inject()

    actual override suspend fun createMultipart(files: List<Form>): MultiPartFormDataContent {
        return multipart(files.map {
            when (it) {
                is Form.FormBody -> {
                    FormFull.FormBodyFull(it.key, it.value)
                }

                is Form.FormFile -> {
                    val file = file(context, it.uri)
                    println("file - $file")
                    FormFull.FormFileFull(
                        key = it.key,
                        name = file?.name.orEmpty(),
                        byteArray = file?.readBytes() ?: byteArrayOf()
                    )
                }

                is Form.FromBodyBool -> {
                    FormFull.FromBodyFullBool(
                        key = it.key,
                        value = it.value
                    )
                }

                is Form.FromBodyInt -> {
                    FormFull.FromBodyFullInt(
                        key = it.key,
                        value = it.value
                    )
                }

            }
        })
    }

    actual override suspend fun createFormData(files: List<Form>): List<PartData> {
        return formData(files.map {
            when (it) {
                is Form.FormBody -> {
                    println("NE FIlIK")
                    FormFull.FormBodyFull(it.key, it.value)
                }

                is Form.FormFile -> {
                    println("FILIK")
                    val file = file(context, it.uri)
                    FormFull.FormFileFull(
                        key = it.key,
                        name = file?.name.orEmpty(),
                        byteArray = file?.readBytes() ?: byteArrayOf()
                    )
                }

                is Form.FromBodyBool -> {
                    FormFull.FromBodyFullBool(
                        key = it.key,
                        value = it.value
                    )
                }

                is Form.FromBodyInt -> {
                    FormFull.FromBodyFullInt(
                        key = it.key,
                        value = it.value
                    )
                }
            }
        })
    }

}