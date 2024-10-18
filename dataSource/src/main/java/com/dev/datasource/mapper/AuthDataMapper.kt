package com.dev.datasource.mapper

import com.dev.auth.data.dto.LoginDto
import com.dev.auth.domain.model.UserInfoDomain
import com.dev.datasource.model.entity.UserInfoEntity
import com.dev.datasource.model.response.LoginResponse

fun LoginResponse.toLoginDto():LoginDto{
    return LoginDto(
        userName = userName,
        userImageUrl = userImageUrl,
        accessToken = accessToken,
        userId = userId
    )
}

fun LoginDto.toUserInfoEntity():UserInfoEntity{
    return UserInfoEntity(
        name = userName,
        imageUrl = userImageUrl,
        userId = userId
    )
}

fun UserInfoEntity.toUserInfoDomain():UserInfoDomain{
    return UserInfoDomain(
        name = name,
        imageUrl = imageUrl,
        userId = userId
    )
}