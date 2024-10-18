package com.dev.datasource.roomDatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.dev.datasource.model.entity.UserInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserInfoDao {

    @Query("SELECT * FROM user_info")
    fun getUserInfo():Flow<UserInfoEntity>

    @Upsert
    suspend fun upsertUserInfo(userInfoEntity: UserInfoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserInfo(userInfoEntity: UserInfoEntity)

    @Query("DELETE FROM user_info")
    suspend fun clearUserInfo()
}