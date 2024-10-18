package com.dev.datasource.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("user_id") val userId:String,
    @SerialName("username") val userName:String,
    @SerialName("user_image_url") val userImageUrl:String,
    @SerialName("access_token") val accessToken:String
)
