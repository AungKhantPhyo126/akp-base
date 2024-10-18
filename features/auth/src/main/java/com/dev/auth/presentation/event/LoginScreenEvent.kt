package com.dev.auth.presentation.event

sealed class LoginScreenEvent{
    data object Idle:LoginScreenEvent()
    data class ClickLogin(val userName:String,val password:String):LoginScreenEvent()
}