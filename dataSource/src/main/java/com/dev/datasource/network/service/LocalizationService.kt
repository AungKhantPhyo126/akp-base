package com.dev.datasource.network.service

import com.dev.datasource.model.response.BaseResponse
import com.dev.datasource.model.response.LocalizationResponse
import com.dev.datasource.network.utils.Constant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface LocalizationService {

    @POST(BuildConfig.LOCALIZATION + Constant.API_VERSION + BuildConfig.METHOD_GET_LOCALIZATION)
    suspend fun getlocalization():Response<BaseResponse<Map<String,LocalizationResponse>>>
}