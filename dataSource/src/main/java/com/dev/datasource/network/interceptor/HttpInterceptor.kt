package com.dev.datasource.network.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.Buffer
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class HttpInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalResponse = chain.proceed(originalRequest)

        val statusCode = originalResponse.code
        val body = originalResponse.body

        val bodyStr = prettifyJson(body?.string().orEmpty())
        val requestBody = originalRequest.body
        val requestBodyString = if (requestBody != null) {
            val buffer = Buffer()
            requestBody.writeTo(buffer)
            buffer.readUtf8()
        } else {
            ""
        }
        val requestStr = prettifyJson(requestBodyString)



        return originalResponse.newBuilder()
            .body(bodyStr.toResponseBody(originalResponse.body?.contentType()))
            .build()
    }

    private fun prettifyJson(jsonString: String): String {
        return try {
            if (jsonString.startsWith("{")) {
                val jsonObject = JSONObject(jsonString)
                jsonObject.toString(2) // 2 isthe number of spaces for indentation
            } else if (jsonString.startsWith("[")) {
                val jsonArray = JSONArray(jsonString)
                jsonArray.toString(2)
            } else {
                // Handle cases where the input is not valid JSON
                Log.e("PrettifyJSON", "Invalid JSON string")
                jsonString // Return the original string if it's not valid JSON
            }
        } catch (e: Exception) {
            Log.e("PrettifyJSON", "Error prettifying JSON: ${e.message}")
            jsonString // Return the original string on error}
        }
    }
}
