package com.dev.datasource

import arrow.core.Either
import com.dev.common.exception.DataException
import com.dev.datasource.model.response.LocalizationResponse

interface LocalizationDataSource {
    suspend fun getLocalization():Either<DataException,Map<String, LocalizationResponse>>
}