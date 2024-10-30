package com.dev.common

import com.dev.common.exception.DataException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

/** this is where you can manage your global state and actions**/
object GlobalStateFlow{
    private val globalMutableStateFlow = MutableStateFlow<GlobalState>(GlobalState.Empty)

    val globalStateFlow: StateFlow<GlobalState> = globalMutableStateFlow

    fun emit(state: GlobalState) {
        globalMutableStateFlow.update { state }
    }

    fun clearState() {
        globalMutableStateFlow.update { GlobalState.Empty }
    }

    sealed class GlobalState{
        data object Empty:GlobalState()
        data object Error401:GlobalState()
        data object Error403 : GlobalState()
        data class Error(val errorMessage:String) : GlobalState()
        data class Maintenance(
            val maintenanceOn: Boolean,
            val title: String,
            val description: String
        ) : GlobalState()
    }
}