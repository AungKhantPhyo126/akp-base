package com.dev.auth.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.dev.auth.domain.repository.AuthRepository
import com.dev.auth.presentation.event.LoginScreenEvent
import com.dev.auth.presentation.state.LoginState
import com.dev.common.LoadingManager
import com.dev.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) :BaseViewModel<LoginState, LoginScreenEvent>(
    LoginState()
){

    private fun login(
        userName:String,
        password:String
    ){
        viewModelScope.launch {
            LoadingManager.showLoading()
            authRepository.login(
                userName, password
            ).tap {
                LoadingManager.hideLoading()
                _uiState.update {
                    it.copy(

                    )
                }
            }.tapLeft {
                LoadingManager.hideLoading()

            }
        }
    }

    fun onEvent(event:LoginScreenEvent){
        when(event){
            is LoginScreenEvent.Idle->{}
            is LoginScreenEvent.ClickLogin->{
                login(userName = event.userName, password = event.password)
            }
        }
    }

}