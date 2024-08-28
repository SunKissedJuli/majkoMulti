package com.example.majkomulti.domain.repository

import com.example.majkomulti.domain.modelsUI.InfoUi
import com.example.majkomulti.platform.Either
import com.example.majkomulti.platform.Failure
import kotlinx.coroutines.flow.Flow

interface InfoRepository {

    suspend fun getStatuses(): Either<Failure, List<InfoUi>>

    suspend fun getPriorities(): Either<Failure, List<InfoUi>>

}