package com.dev.datasource

import arrow.core.Either
import com.dev.common.exception.DataException
import com.dev.datasource.dataStore.MyDataStoreDataSource
import com.dev.datasource.model.request.LoginRequest
import com.dev.datasource.model.response.LoginResponse
import com.dev.datasource.network.handler.handleCall
import com.dev.datasource.network.service.AuthService
import com.dev.datasource.roomDatabase.MyRoomDataSource
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authService: AuthService,
    private val myRoomDataSource: MyRoomDataSource,
    private val myDataStoreDataSource: MyDataStoreDataSource,
):AuthDataSource {
    override suspend fun login(userName:String,password:String): Either<DataException, LoginResponse> {
        return handleCall(
            apiCall = {
                authService.login(
                    LoginRequest(username = userName,password = password)
                )
            },
            mapper = { data, _, _ ->
                data
            }
        )
    }

    override suspend fun register(): String {
        TODO("Not yet implemented")
    }
}