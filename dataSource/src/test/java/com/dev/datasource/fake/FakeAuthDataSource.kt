package com.dev.datasource.fake

import arrow.core.Either
import com.dev.common.exception.DataException
import com.dev.datasource.AuthDataSource
import com.dev.datasource.model.response.LoginResponse


class FakeAuthDataSource: AuthDataSource {

    private var apiException: DataException.Api? = null
    private val fakeResponse = LoginResponse(
        userId = "123",
        userName = "akp",
        userImageUrl = "akpImage",
        accessToken = "Bhiephif"
    )

    override suspend fun login(
        userName: String,
        password: String
    ): Either<DataException, LoginResponse> {
        return if (apiException != null) {
            Either.Left(apiException!!)
        } else {
            Either.Right(fakeResponse)
        }
    }

    override suspend fun register(): String {
        TODO("Not yet implemented")
    }
}