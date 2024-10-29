package com.dev.datasource

import arrow.core.Either
import com.dev.common.exception.DataException
import com.dev.datasource.model.response.LocalizationResponse
import com.dev.datasource.network.handler.handleCall
import javax.inject.Inject

class LocalizationDataSourceImpl @Inject constructor(
    private val service:LocalizationService
) : LocalizationDataSource {
    override suspend fun getLocalization(): Either<DataException, Map<String, LocalizationResponse>> {
        return handleCall(
            apiCall = {
                service.getLocalization()
            },
            mapper = {data,_,_->
                data
            }
        )
    }
}