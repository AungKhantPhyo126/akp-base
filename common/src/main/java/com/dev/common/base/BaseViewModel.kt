package com.dev.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<S, E>(
    initialState: S
) : ViewModel() {

    protected val _uiState = MutableStateFlow(initialState)
    open val uiState = _uiState.asStateFlow()

    protected val _event = MutableSharedFlow<E>()
    val event: SharedFlow<E> = _event.asSharedFlow()

    protected fun emitEvent(event: E) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }
}