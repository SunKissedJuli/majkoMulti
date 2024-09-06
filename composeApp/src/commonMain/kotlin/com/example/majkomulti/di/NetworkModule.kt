package com.example.majkomulti.di

import com.example.majkomulti.commons.Constantas
import com.example.majkomulti.commons.toToken
import com.example.majkomulti.domain.manager.AuthManager
import com.example.majkomulti.platform.Failure
import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpRedirect
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpSendPipeline
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import kotlinx.serialization.json.Json

internal val networkModule = module {
    factoryOf(::provideJson)
    factoryOf(::provideHttpClient)
    factory { provideKtorHttpClient(get(), Constantas.BASE_URL, get()) }

}

@OptIn(ExperimentalSerializationApi::class)
private fun provideJson(): Json {
    return Json {
        isLenient = true
        ignoreUnknownKeys = true
        explicitNulls = false
        encodeDefaults = true
        prettyPrint = true
    }
}

private const val TIME_OUT = 60000L

private fun provideHttpClient(
    json: Json,
) = HttpClient {
    install(HttpRedirect) {
        checkHttpMethod = false
        allowHttpsDowngrade = false
    }

    defaultRequest {
        header("Content-Type", "application/json")
      //  header("Content-Type", "multipart/form-data")
    }


    install(ContentNegotiation) {
        json(json)
    }

    HttpResponseValidator {

        handleResponseExceptionWithRequest { cause, request ->
            println(cause)
            println(request)
        }

        validateResponse { response ->

            val error =
                response.status != HttpStatusCode.OK
                        && response.status != HttpStatusCode.Created
                        && response.status != HttpStatusCode.NoContent
                        && response.status != HttpStatusCode.BadRequest
                        && response.status != HttpStatusCode.Forbidden

            if (error) {
                val body = response.bodyAsText()

                tryCatch {
                    val message =
                        json.decodeFromString(ErrorMessage.serializer(), body)
                    message.detail ?: return@tryCatch
                    throw Failure.Http(code = response.status.value, message.detail)
                }

                tryCatch {
                    val message =
                        json.decodeFromString(ErrorDetailMessage.serializer(), body)
                    message.detail?.message ?: return@tryCatch
                    throw Failure.Http(
                        code = response.status.value,
                        message.detail.message
                    )
                }

                tryCatch {
                    val message =
                        json.decodeFromString(Message.serializer(), body)
                    message.message ?: return@tryCatch
                    throw Failure.Http(
                        code = response.status.value,
                        message.message
                    )
                }

                tryCatch {
                    val message =
                        json.decodeFromString(Message.serializer(), body)
                    message.message ?: return@tryCatch
                    throw Failure.Http(
                        code = response.status.value,
                        message.message
                    )
                }
                throw Failure.Message("Попробуйте позже")

            }
        }
    }

    install(HttpTimeout) {
        connectTimeoutMillis = TIME_OUT
        socketTimeoutMillis = TIME_OUT
        requestTimeoutMillis = TIME_OUT
    }

    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                println("json:\n ${message}")
            }

        }
        level = LogLevel.ALL
    }

}

private fun provideKtorHttpClient(
    httpClient: HttpClient,
    baseUrl: String,
    authManager: AuthManager
): Ktorfit {
    httpClient.sendPipeline.intercept(HttpSendPipeline.State){
        if(authManager.token!=null){
            context.headers["Authorization"] = authManager.token?.toToken()?:""
        }

    }
    return ktorfit {
        baseUrl(baseUrl)
        httpClient(httpClient)
    }
}

private suspend fun tryCatch(body: suspend () -> Unit) {
    try {
        body()
    } catch (e: Exception) {
    }
}

@Serializable
private class ErrorMessage(val detail: String?)

@Serializable
private class ErrorDetailMessage(val detail: Message?)

@Serializable
private class Message(val message: String?)