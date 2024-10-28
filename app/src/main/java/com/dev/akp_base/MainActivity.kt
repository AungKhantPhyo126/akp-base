package com.dev.akp_base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.dev.akp_base.navigation.AkpBaseNavHost
import com.dev.designsystem.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AkpBaseApp()
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