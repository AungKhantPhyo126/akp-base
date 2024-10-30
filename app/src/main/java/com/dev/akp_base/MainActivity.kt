package com.dev.akp_base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.rememberNavController
import com.dev.akp_base.localization.LocalLocalization
import com.dev.akp_base.localization.model.LocalizationModel
import com.dev.akp_base.navigation.AkpBaseNavHost
import com.dev.common.GlobalStateFlow
import com.dev.common.promptUserMessage.UserMessageManager
import com.dev.designsystem.ui.components.dialog.MyAlertDialog
import com.dev.designsystem.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    private var localization by mutableStateOf(LocalizationModel())
    private var globalErrorMessage by mutableStateOf("")
    private var showSessionExpiredDialog by mutableStateOf(false)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.localizationFlow.collectLatest { localizationModel ->
                    localization = localizationModel
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.globalStateFlow.collectLatest { globalStateFlow ->
                    GlobalStateFlow.globalStateFlow.collectLatest {
                        when (it) {
                            is GlobalStateFlow.GlobalState.Error401 -> showSessionExpiredDialog =
                                true

                            is GlobalStateFlow.GlobalState.Error -> {
                                globalErrorMessage = it.errorMessage
                            }

                            is GlobalStateFlow.GlobalState.Error403 -> {
                                UserMessageManager.showMessage(localization.appNotAvailableInRegion)
                                GlobalStateFlow.clearState()
                            }

                            else -> Unit
                        }
                    }
                }
            }
        }
        setContent {
            AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AkpBaseApp()
                    Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
                        CompositionLocalProvider(
                            LocalLocalization provides localization
                        ) {
                            globalErrorMessage.let {
                                if (it.isNotEmpty()){
                                    MyAlertDialog(
                                        title = LocalLocalization.current.error,
                                        body = it,
                                        confirmButton = {
                                            globalErrorMessage = ""
                                            GlobalStateFlow.clearState()
                                        }
                                    )
                                }
                            }

                            if(showSessionExpiredDialog){
                                MyAlertDialog(
                                    title = LocalLocalization.current.sessionExpireTitle,
                                    body = LocalLocalization.current.sessionExpireBody,
                                    properties = DialogProperties(
                                        dismissOnBackPress = false,
                                        dismissOnClickOutside = false
                                    ),
                                    confirmButton = {
                                        //to call logout from viewModel here
                                        showSessionExpiredDialog = false
                                    }
                                )
                            }

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AkpBaseApp() {
    AppTheme {
        val navController = rememberNavController()
        Scaffold(
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.statusBars), // Adjust for the status bar
        ) { innerPadding ->
            AkpBaseNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}