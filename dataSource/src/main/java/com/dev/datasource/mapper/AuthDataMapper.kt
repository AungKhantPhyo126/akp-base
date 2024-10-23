package com.dev.datasource.mapper

import com.dev.datasource.model.entity.UserInfoEntity
import com.dev.datasource.model.response.LoginResponse

fun LoginResponse.toUserInfoEntity():UserInfoEntity{
    return UserInfoEntity(
        name = userName,
        imageUrl = userImageUrl,
        userId = userId
    )
}

