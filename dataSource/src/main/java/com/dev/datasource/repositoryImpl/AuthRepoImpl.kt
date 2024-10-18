package com.dev.datasource.repositoryImpl

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.right
import com.dev.auth.data.AuthDataSource
import com.dev.auth.data.dto.LoginDto
import com.dev.auth.domain.model.UserInfoDomain
import com.dev.auth.domain.repository.AuthRepository
import com.dev.common.exception.DataException
import com.dev.datasource.dataStore.MyDataStoreDataSource
import com.dev.datasource.mapper.toUserInfoDomain
import com.dev.datasource.mapper.toUserInfoEntity
import com.dev.datasource.roomDatabase.MyRoomDataSource
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val myDataStoreDataSource: MyDataStoreDataSource,
    private val myRoomDataSource: MyRoomDataSource
):AuthRepository {
    override suspend fun login(userName:String,password:String): Either<DataException, UserInfoDomain> {
        return authDataSource.login(
            userName = userName,
            password = password
        ).flatMap { result->
            myDataStoreDataSource.saveAccessToken(result.accessToken)
            myRoomDataSource.saveUserInfo(result.toUserInfoEntity())
            val userInfo = myRoomDataSource.getUserInfo()
            Either.Right(userInfo.first().toUserInfoDomain())
        }
    }
}