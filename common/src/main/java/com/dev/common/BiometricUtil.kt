package com.dev.common

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_WEAK
import androidx.biometric.BiometricPrompt

import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

object BiometricUtil {
    fun isDeviceSupportBiometric(context: Context): Boolean {
        val manager = BiometricManager.from(context)
        return manager.canAuthenticate(
            BIOMETRIC_STRONG or BIOMETRIC_WEAK
        ) == BiometricManager.BIOMETRIC_SUCCESS
    }

    fun showBiometricPrompt(
        activity: FragmentActivity,
        title: String = "Confirm Your Identity",
        subtitle: String = "Use Biometrics to confirm your identity.",
        description: String = "",
        success: () -> Unit,
        fail: (message: String) -> Unit
    ) {
        // 1
        val promptInfo = setBiometricPromptInfo(
            title = title,
            subtitle = subtitle,
            description = description
        )

        // 2
        val biometricPrompt = initBiometricPrompt(activity, fail, success)

        // 3
        biometricPrompt.authenticate(promptInfo)
    }

    private fun setBiometricPromptInfo(
        title: String,
        subtitle: String,
        description: String,
    ): BiometricPrompt.PromptInfo {
        return BiometricPrompt
            .PromptInfo
            .Builder()
            .setTitle(title)
            .setSubtitle(subtitle)
            .setDescription(description)
            .setNegativeButtonText("Cancel")
            .build()
    }

    private fun initBiometricPrompt(
        activity: FragmentActivity,
        fail: (String) -> Unit,
        success: () -> Unit
    ): BiometricPrompt {
        val executor = ContextCompat.getMainExecutor(activity)

        val callback = object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                fail.invoke(errString.toString())
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                fail.invoke("Fail to register")
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                success.invoke()
            }
        }

        return BiometricPrompt(activity, executor, callback)
    }
}