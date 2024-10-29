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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.rememberNavController
import com.dev.akp_base.localization.LocalLocalization
import com.dev.akp_base.localization.model.LocalizationModel
import com.dev.akp_base.navigation.AkpBaseNavHost
import com.dev.designsystem.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    private var localization by mutableStateOf(LocalizationModel())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.localizationFlow.collectLatest{localizationModel->
                    localization = localizationModel
                }
            }
        }
        setContent {
            AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AkpBaseApp()
                    Box(modifier = Modifier.fillMaxSize()){
                        CompositionLocalProvider(
                            LocalLocalization provides localization
                        ) {

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
                .windowInsetsPadding(WindowInsets.statusBars) // Adjust for the status bar
            ,
        ) { innerPadding ->
            AkpBaseNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}