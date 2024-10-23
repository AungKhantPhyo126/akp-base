package com.dev.datasource

import arrow.core.Either
import com.dev.common.exception.DataException
import com.dev.datasource.model.response.LoginResponse

interface AuthDataSource {
    suspend fun login(userName:String,password:String):Either<DataException, LoginResponse>
    suspend fun register():String
}