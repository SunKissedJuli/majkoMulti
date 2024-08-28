package com.example.majkomulti.data.repository

import com.example.majkomulti.data.api.MajkoApi
import com.example.majkomulti.data.mapper.toUI
import com.example.majkomulti.domain.modelsUI.InfoUi
import com.example.majkomulti.domain.repository.InfoRepository
import com.example.majkomulti.platform.Either
import com.example.majkomulti.platform.Failure
import com.example.majkomulti.platform.apiCall


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

}