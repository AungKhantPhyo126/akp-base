package com.dev.datasource.model.response

import kotlinx.serialization.SerialName

data class LocalizationResponse(
    @SerialName("english") val english:String,
    @SerialName("myanmar") val myanmar:String,
)
