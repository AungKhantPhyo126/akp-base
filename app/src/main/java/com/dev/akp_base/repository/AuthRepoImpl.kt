package com.dev.akp_base.repository

import arrow.core.Either
import arrow.core.flatMap
import com.dev.auth.domain.model.UserInfoDomain
import com.dev.auth.domain.repository.AuthRepository
import com.dev.common.exception.DataException
import com.dev.datasource.AuthDataSource
import com.dev.datasource.dataStore.MyDataStoreDataSource
import com.dev.datasource.mapper.toUserInfoEntity
import com.dev.datasource.model.entity.UserInfoEntity
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


fun UserInfoEntity.toUserInfoDomain():UserInfoDomain{
    return UserInfoDomain(
        name = name,
        imageUrl = imageUrl,
        userId = userId
    )
}