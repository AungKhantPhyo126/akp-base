package com.dev.datasource.model.response

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class BaseResponse<T>(
    @SerialName("message")
    val message: String?,

    @SerialName("code")
    val errorCode: Int?,

    @SerialName("data")
    val data: T? = null
)