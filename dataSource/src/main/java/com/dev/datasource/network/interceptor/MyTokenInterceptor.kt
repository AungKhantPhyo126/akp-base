package com.dev.datasource.network.interceptor

import com.dev.datasource.BuildConfig
import com.dev.datasource.dataStore.MyDataStoreDataSource
import com.dev.datasource.roomDatabase.MyRoomDataSource
import okhttp3.Interceptor
import okhttp3.MultipartBody
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.net.URL
import java.nio.charset.Charset

fun String.prefixBearer() = "Bearer $this"
fun myTokenInterceptor(
    chain: Interceptor.Chain,
    myDataStoreDataSource: MyDataStoreDataSource
): Response {
    val accessToken = myDataStoreDataSource.getAccessToken()
    val originalRequest = chain.request()

    // Create a new URL with the new base URL
    val baseURL = URL(BuildConfig.BASE_URL)
    val newUrl = originalRequest.url.newBuilder()
        .scheme(baseURL.protocol)
        .host(baseURL.host)
        .build()

    // Create a new request with the new URL
    val newRequest = originalRequest.newBuilder()
        .url(newUrl)
        .build()
    val url = newRequest.url.toString()

    val contentType = if (newRequest.body is MultipartBody) {
        newRequest.body?.contentType()?.toString().orEmpty()
    } else {
        "application/json-patch+json"
    }

    val request = newRequest.newBuilder()
        .addHeader("Content-Type", contentType)
        .apply {
            if (!url.endsWith(BuildConfig.LOGIN_BIOMETRIC)) {
                when {
                    accessToken.isNotBlank() -> addHeader(
                        "Authorization",
                        accessToken.prefixBearer()
                    )

                    else -> addHeader("Authorization", BuildConfig.BASIC_TK)
                }
            }
        }
        .addHeader("accept", "text/plain")
        .build()
    return chain.proceed(request = request)
}

class ResponseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val body = response.body
        val source = body?.source()
        source?.request(Long.MAX_VALUE)
        val buffer = source?.buffer
        val rawJson = buffer?.clone()?.readString(Charset.forName("UTF-8"))

        return response.newBuilder()
            .body(rawJson?.toResponseBody(body.contentType()))
            .build()
    }
}
