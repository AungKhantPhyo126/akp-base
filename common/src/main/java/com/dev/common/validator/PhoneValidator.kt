package com.dev.common.validator

import java.util.regex.Matcher
import java.util.regex.Pattern


object PhoneValidator {
    private const val PHONE_REGEX = "^[+]?[0-9]{9,13}\$"
    private var pattern: Pattern = Pattern.compile(PHONE_REGEX, Pattern.CASE_INSENSITIVE)
    private lateinit var matcher: Matcher
    fun validate(number: String): Boolean {
        matcher = pattern.matcher(number)
        return number.length >= 9 && matcher.matches()
    }

    fun hasOnlyDigits(number: String) : Boolean = try {
        if (number.isEmpty())
            false
        else Pattern.matches("[0-9]+", number)
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }
}
