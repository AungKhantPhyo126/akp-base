package com.dev.common.dateTime

import com.dev.common.dateTime.DateFormat.PATTERN_FOUR
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale

val currentDate: LocalDate get() = LocalDate.now()

val currentDateTime: LocalDateTime get() = LocalDateTime.now()

val LocalDateTime.millis: Long
    get() = toInstant(ZonedDateTime.now().offset).toEpochMilli()

fun LocalDate.format(
    pattern: String,
    default: String = "-"
): String {
    return try {
        format(DateTimeFormatter.ofPattern(pattern))
    } catch (e: Exception) {
        default
    }
}

fun LocalDateTime.format(
    pattern: String,
    default: String = "-"
): String {
    return try {
        format(DateTimeFormatter.ofPattern(pattern))
    } catch (e: Exception) {
        default
    }
}

fun Long.format(
    pattern: String,
    default: String = "-"
): String {
    return try {
        val localDate = this.toLocalDate()
        localDate.format(DateTimeFormatter.ofPattern(pattern))
    } catch (e: Exception) {
        default
    }
}

fun String.toLocalDate(originPattern: String): LocalDate {
    return LocalDate.parse(this, DateTimeFormatter.ofPattern(originPattern, Locale.ENGLISH))
}

fun String.toLocalDateTime(originPattern: String): LocalDateTime {
    return LocalDateTime.parse(this, DateTimeFormatter.ofPattern(originPattern, Locale.ENGLISH))
}

fun String.toLocalTime(originPattern: String): LocalTime {
    val formatter = DateTimeFormatter.ofPattern(originPattern, Locale.getDefault())
    return LocalTime.parse(this, formatter)
}

fun Long.toLocalDate(): LocalDate {
    return Instant.ofEpochMilli(this).atZone(ZoneId.systemDefault()).toLocalDate()
}

fun String?.changeFormat(
    originPattern: String,
    targetPattern: String,
    isUTC: Boolean = true,
    default: String = "-"
): String {
    if (this.isNullOrBlank()) return default
    return try {
        val fromP = DateTimeFormatter.ofPattern(originPattern)
        val toP = DateTimeFormatter.ofPattern(targetPattern)
        val fromLocalDate = LocalDateTime.parse(this, fromP)
        if (isUTC) {
            val zonedDateTime = fromLocalDate.atZone(ZoneOffset.UTC)
            // Convert to local date time
            val localDateTime = zonedDateTime.withZoneSameInstant(ZoneId.systemDefault())
            return localDateTime.format(toP)
        }
        return fromLocalDate.format(toP)
    } catch (e: Exception) {
        e.printStackTrace()
        this
    }
}

fun String?.changeTimeFormat(
    originPattern: String,
    targetPattern: String,
    default: String = "-"
): String {
    if (this == null) return default
    try {
        val inputFormat = SimpleDateFormat(originPattern, Locale.getDefault())
        val outputFormat = SimpleDateFormat(targetPattern, Locale.getDefault())
        val date = inputFormat.parse(this) ?: return default
        return outputFormat.format(date)
    } catch (e: Exception) {
        return this
    }
}

fun getNextSevenDays(startDate: LocalDate): List<String> {
    val pattern = DateTimeFormatter.ofPattern(PATTERN_FOUR)
    val dates = mutableListOf<String>()
    for (i in 0..7) {
        dates.add(startDate.plusDays(i.toLong()).format(pattern))
    }
    return dates
}

fun LocalDate.isWithinNextSevenDays(startDate: LocalDate): Boolean {
    if (isEqual(startDate)) return true
    val endDate = startDate.plusDays(7)
    return isAfter(startDate) && isBefore(endDate)
}

fun getDayDifference(startDate: LocalDate, endDate: LocalDate): Long {
    return ChronoUnit.DAYS.between(startDate, endDate)
}

fun String?.isToadyDate(pattern: String): Boolean {
    return try {
        val format = DateTimeFormatter.ofPattern(pattern)
        currentDate == LocalDate.parse(this, format)
    } catch (e: Exception) {
        false
    }

}

fun LocalDate.isYearOld(year: Int): Boolean {
    val today = currentDate
    val yearOld = today.minusYears(15)
    return this.isBefore(yearOld)
}

fun String.totalMinutes(): Int {
    val localTime = LocalTime.parse(this)
    return localTime.toSecondOfDay() / 60
}