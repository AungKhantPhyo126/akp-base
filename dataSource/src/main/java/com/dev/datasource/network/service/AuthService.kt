package com.dev.datasource.network.service

import com.dev.datasource.BuildConfig
import com.dev.datasource.model.request.LoginRequest
import com.dev.datasource.model.response.BaseResponse
import com.dev.datasource.model.response.LoginResponse
import com.dev.datasource.network.utils.Constant
import retrofit2.Response
import retrofit2.http.POST

interface AuthService{

    @POST(BuildConfig.AUTH + Constant.API_VERSION + BuildConfig.METHOD_POST_LOGIN)
    suspend fun login(
        request: LoginRequest
    ): Response<BaseResponse<LoginResponse>>
}