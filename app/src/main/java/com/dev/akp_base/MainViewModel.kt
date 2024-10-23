package com.dev.akp_base

import com.dev.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel<MainActivityState,MainActivityEvent>(
    initialState = MainActivityState()
) {

    fun onEvent(event: MainActivityEvent){
        when(event){
            MainActivityEvent.HomeScreenButtonClick ->{

            }
            MainActivityEvent.Idle -> {}
        }
    }

}

data class MainActivityState(
    val isLoading:Boolean = false
)

sealed class MainActivityEvent{
    data object Idle:MainActivityEvent()
    data object HomeScreenButtonClick : MainActivityEvent()
}