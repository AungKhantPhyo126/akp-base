package com.dev.common.dateTime

import com.dev.common.dateTime.DateFormat.PATTERN_FOUR
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateUtil {
    fun getNextDays(startDate: LocalDate, daysCount: Int): List<String> {
        val pattern = DateTimeFormatter.ofPattern(PATTERN_FOUR)
        val dates = mutableListOf<String>()
        for (i in 0..daysCount) {
            dates.add(startDate.plusDays(i.toLong()).format(pattern))
        }
        return dates
    }

    fun getPreviousDays(startDate: LocalDate, daysCount: Int): List<String> {
        val pattern = DateTimeFormatter.ofPattern(PATTERN_FOUR)
        val dates = mutableListOf<String>()
        for (i in daysCount downTo  1) {
            dates.add(startDate.minusDays(i.toLong()).format(pattern))
        }
        return dates
    }
}