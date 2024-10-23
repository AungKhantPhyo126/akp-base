package com.dev.datasource

import com.dev.datasource.model.request.LoginRequest
import com.dev.datasource.model.response.BaseResponse
import com.dev.datasource.model.response.LoginResponse
import com.dev.datasource.network.service.AuthService
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import retrofit2.Response

class AuthServiceTest {

    private val authService = mockk<AuthService>()
    private val successLoginRequest = LoginRequest(
        username = "user1",
        password = "password1"
    )

    private val failLoginRequest = LoginRequest(
        username = "user1",
        password = "password1"
    )

    private val successLoginResponse = LoginResponse(
        userId = "123",
        userName = "akp",
        userImageUrl = "akpImage",
        accessToken = "heiqepih3"
    )

    private val failureLoginResponse = LoginResponse(
        userId = "akp123",
        userName = "akp322",
        userImageUrl = "akpImage",
        accessToken = "heiqepih3"
    )

    @Test
    fun `test authentication success`() = runTest {
        coEvery { authService.login(successLoginRequest) } returns successLoginResponse

        val isAuthenticated = authService.login(successLoginRequest)
        assertEquals(true, isAuthenticated)
    }

    @Test
    fun `test authentication failure`()  = runTest{
        coEvery { authService.login(failLoginRequest) } returns false

        val isAuthenticated = authService.login(failLoginRequest)
        assertEquals(false, isAuthenticated)
    }

}
