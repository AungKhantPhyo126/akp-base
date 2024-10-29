package com.dev.akp_base

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dev.akp_base.localization.LocalizationRepository
import com.dev.akp_base.localization.model.LocalizationModel
import com.dev.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val localizationRepository: LocalizationRepository
) : BaseViewModel<MainActivityState,MainActivityEvent>(
    initialState = MainActivityState()
) {
    val localizationFlow = localizationRepository.localizationFlow
        .stateIn(
            scope = viewModelScope,
            initialValue = LocalizationModel(),
            started = SharingStarted.WhileSubscribed(5000L)
        )

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