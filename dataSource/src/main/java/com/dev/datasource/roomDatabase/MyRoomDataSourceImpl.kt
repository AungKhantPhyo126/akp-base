package com.dev.datasource.roomDatabase

import com.dev.datasource.model.entity.UserInfoEntity
import com.dev.datasource.roomDatabase.dao.UserInfoDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MyRoomDataSourceImpl @Inject constructor(
   private val userInfoDao: UserInfoDao
) : MyRoomDataSource {
    override suspend fun saveUserInfo(userInfoEntity: UserInfoEntity) {
        userInfoDao.insertUserInfo(userInfoEntity)
    }

    override fun getUserInfo(): Flow<UserInfoEntity> {
        return userInfoDao.getUserInfo()
    }


}