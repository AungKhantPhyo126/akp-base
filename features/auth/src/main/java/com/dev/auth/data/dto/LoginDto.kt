package com.dev.auth.data.dto

import kotlinx.serialization.SerialName

data class LoginDto(
    val userId:String,
    val userName: String,
    val userImageUrl: String,
    val accessToken: String
)
