package com.dev.datasource.network.utils

import javax.inject.Inject
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class MyAuthenticator @Inject constructor() : Authenticator {

    //here you can implement refresh token if your backend support.
    override fun authenticate(route: Route?, response: Response): Request? {
        return try {
            when (response.code) {
                401 -> onAuthorizationError()
                else -> null
            }
        } catch (e: Exception) {
            onAuthorizationError()
            null
        }
    }

    private fun onAuthorizationError(): Request? {
        return null
    }
}
