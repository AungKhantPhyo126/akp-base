package com.dev.unit_test.data

import arrow.core.Either
import com.dev.common.exception.DataException

interface CoinDataSource {
    suspend fun getCoin():Either<DataException,CoinDto>
}