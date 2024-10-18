package com.dev.datasource.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("user_info")
data class UserInfoEntity(
    @PrimaryKey
    val userId:String,
    val name:String,
    val imageUrl:String
)
