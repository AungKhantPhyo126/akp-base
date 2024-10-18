package com.dev.common

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStreamReader
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import kotlin.math.abs

fun isRooted(): Boolean {
    val buildTags = android.os.Build.TAGS
    return buildTags != null && buildTags.contains("test-keys")
            || "/system/app/Superuser.apk".exists()
            || "/system/xbin/su".exists()
}

private fun String.exists(): Boolean {
    return try {
        val file = File(this)
        file.exists()
    } catch (e: Exception) {
        false
    }
}

fun joinTo(first: String, second: String, separator: String = ","): String {
    return if (first.isNotEmpty() && second.isNotEmpty()) {
        "$first$separator$second"
    } else {
        first.ifEmpty { second }
    }
}

fun isBootloaderUnlocked(): Boolean {
    try {
        val process = Runtime.getRuntime().exec("fastboot getvar unlocked")

        val reader = BufferedReader(InputStreamReader(process.inputStream))
        var line: String?

        while (reader.readLine().also { line = it } != null) {
            // Check if the output contains the unlocked status
            if (line?.trim() == "unlocked: yes") {
                return true
            }
        }
        process.waitFor()
        process.destroy()
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return false
}

fun compressImage(bytes: ByteArray, maxFileSizeInKB: Int): ByteArray? {
    val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    var quality = 100
    val stream = ByteArrayOutputStream()

    // Compress the image while quality is above 0 and the file size is greater than the desired size
    do {
        stream.reset()
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, stream)
        quality -= 5
    } while (stream.size() / 1024 > maxFileSizeInKB && quality > 0)

    return stream.toByteArray()
}

fun findNearestDate(dates: List<String>): String? {
    // Define the date format (yyyy-MM-dd HH:mm)
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val currentTime = LocalDateTime.now()

    return dates
        .map { dateString ->
            val parsedDate = LocalDateTime.parse(dateString, dateFormatter)
            parsedDate to abs(currentTime.toEpochSecond(ZoneOffset.UTC) - parsedDate.toEpochSecond(ZoneOffset.UTC))
        }
        .minByOrNull { it.second }?.first?.format(dateFormatter) // Get the date with the smallest difference
}