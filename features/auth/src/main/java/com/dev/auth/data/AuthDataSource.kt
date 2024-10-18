package com.dev.auth.data

import arrow.core.Either
import com.dev.auth.data.dto.LoginDto
import com.dev.common.exception.DataException

interface AuthDataSource {
    suspend fun login(userName:String,password:String):Either<DataException, LoginDto>
    suspend fun register():String
}