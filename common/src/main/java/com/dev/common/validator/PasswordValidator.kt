package com.dev.common.validator

object PasswordValidator {
    fun validate(password: String): Boolean {
        val valid8Characters = password.length in 8..32
        val validSpecialCharacters = password.matches(".*[!@#$%^&*+=/?].*".toRegex())
        val validUppercaseLetter = password.matches(".*[A-Z].*".toRegex())
        val validLowercaseLetter = password.matches(".*[a-z].*".toRegex())
        val validContainNumber = password.matches(Regex(".*\\d.*"))
        return valid8Characters && validContainNumber
    }
    fun validateLetterCount(password: String):Boolean = password.length in 8..32
    fun validateContainNumber(password: String):Boolean = password.matches(Regex(".*\\d.*"))
}
