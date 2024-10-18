package com.dev.common.validator

import java.util.regex.Matcher
import java.util.regex.Pattern

object EmailValidator {
    private const val EMAIL_REGEX = "^[\\w-+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$"
    private var pattern: Pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE)
    private lateinit var matcher: Matcher

    fun validate(email: String): Boolean {
        matcher = pattern.matcher(email)
        return matcher.matches()
    }
}
