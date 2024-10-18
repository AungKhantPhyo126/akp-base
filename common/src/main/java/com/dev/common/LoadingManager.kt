package com.dev.common

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object LoadingManager {
    private val _loading = MutableSharedFlow<Boolean>()
    val loading: SharedFlow<Boolean> = _loading.asSharedFlow()

    suspend fun showLoading() {
        _loading.emit(true)
    }

    suspend fun hideLoading() {
        _loading.emit(false)
    }
}