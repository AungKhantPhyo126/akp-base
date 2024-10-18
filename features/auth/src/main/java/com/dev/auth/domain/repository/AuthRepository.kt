package com.dev.auth.domain.repository

import arrow.core.Either
import com.dev.auth.data.dto.LoginDto
import com.dev.auth.domain.model.UserInfoDomain
import com.dev.common.exception.DataException

interface AuthRepository {
    suspend fun login(userName:String,password:String): Either<DataException, UserInfoDomain>
}