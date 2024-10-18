package com.dev.datasource.roomDatabase

import com.dev.datasource.model.entity.UserInfoEntity
import kotlinx.coroutines.flow.Flow

interface MyRoomDataSource {
    suspend fun saveUserInfo(userInfoEntity: UserInfoEntity)
    fun getUserInfo():Flow<UserInfoEntity>
}