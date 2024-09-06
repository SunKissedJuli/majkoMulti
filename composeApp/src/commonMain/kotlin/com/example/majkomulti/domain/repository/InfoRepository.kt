package com.example.majkomulti.domain.repository

import com.example.majkomulti.domain.modelsUI.InfoUi
import com.example.majkomulti.platform.Either
import com.example.majkomulti.platform.Failure
import de.jensklingenberg.ktorfit.http.Part
import kotlinx.coroutines.flow.Flow
import java.io.File

interface InfoRepository {

    suspend fun getStatuses(): Either<Failure, List<InfoUi>>

    suspend fun getPriorities(): Either<Failure, List<InfoUi>>

    suspend fun uploadFile(taskId: String, files: File) :  Either<Failure, Unit>



}