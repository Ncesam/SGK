package com.ncesam.sgk2026.data.utils

import com.ncesam.sgk2026.domain.states.PasswordRules
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun validatePassword(password: String): PasswordRules {
	return PasswordRules(
		minLength = password.length > 8,
		hasUpper = password.any { char -> char.isUpperCase() },
		hasLower = password.any { char -> char.isLowerCase() },
		hasDigit = password.any { char -> char.isDigit() },
		hasSpecial = password.any { char -> char in """!@#\$%^&*()_+-=[]{}|;:'\\\",.<>?/""" }
	)
}

fun validateDate(date: String?): Boolean {
	if (date.isNullOrBlank()) return false
	val formatter = DateTimeFormatter.ISO_DATE
	try {
		LocalDate.parse(date, formatter)
		return true
	} catch (e: Exception) {
		return false
	}
}


fun validateEmail(email: String?): Boolean {
	if (email.isNullOrBlank()) return false
	val pattern = """[a-z0-9]+@[a-z]+\.[a-z]+""".toRegex()
	return pattern.matches(email)
}
