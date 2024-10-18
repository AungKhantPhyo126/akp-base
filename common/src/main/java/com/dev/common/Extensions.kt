package com.dev.common

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.ContentResolver
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.OpenableColumns
import android.provider.Settings
import android.util.Base64
import androidx.core.net.toUri
import androidx.fragment.app.FragmentActivity
import java.io.File
import java.io.FileNotFoundException
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.security.InvalidKeyException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec


infix fun Float.ofPercent(rate: Int): Float = if (rate <= this) 100 * rate / this else 100f
fun Float.toFloatOfPercent() = this / 100
fun Context.openMapView(
    lat: String,
    lng: String
) {
    try {
        val geoUri = "http://maps.google.com/maps?q=loc:$lat,$lng"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(geoUri))
        intent.setPackage("com.google.android.apps.maps")
        startActivity(intent)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun Context.openBrowser(uriString: String) {
    if (uriString.isNotBlank()) {
        try {
            val url = if (!uriString.startsWith("http://") && !uriString.startsWith("https://")) {
                "http://$uriString"
            } else uriString
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browserIntent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

fun Context.shareLink(link: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, link)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(shareIntent)
}

fun Context.sendMail(email: String) {
    try {
        val emailIntent = Intent(
            Intent.ACTION_SENDTO,
            Uri.fromParts("mailto", email, null)
        )
        startActivity(Intent.createChooser(emailIntent, "Send email..."))
    } catch (e: ActivityNotFoundException) {
        e.printStackTrace()
    }
}

fun Context.openDial(contact: String) {
    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$contact"))
    try {
        startActivity(intent)
    } catch (s: SecurityException) {
        s.printStackTrace()
    }
}

fun Context.openPlayStore(storeLink: String) {

    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(storeLink)
        setPackage("com.android.vending")
    }
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    } else {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(storeLink))
        startActivity(browserIntent)
    }

}

fun Double?.asMoney(): String {
    val symbols = DecimalFormatSymbols.getInstance(Locale.ENGLISH)
    return if (this != null) {
        DecimalFormat(
            "#######,###",
            symbols
        ).format(this)
    } else ""
}

fun String.asMoneyUnit(): String {
    return "$this MMK"
}

fun Double?.asMoneyPostfixUnit(): String {
    return "${asMoney()} MMK"
}

fun Double?.asMoneyPrefixUnit(): String {
    return "MMK ${asMoney()}"
}

fun String.asUnitEn(): String {
    return if (this == "-" || this.isBlank()) "0 Unit(s)" else "$this Unit(s)"
}

fun String.asUnitMM(): String {
    return if (this == "-" || this.isBlank()) "၀ ယူနစ်" else "$this ယူနစ်"
}

fun String.prefixBearer() =
    "Bearer $this"

fun Uri.toFile(context: Context): File {
    val fileName = context.contentResolver.getFileName(this)
    return context.contentResolver
        .openInputStream(this)
        .use { inputStream ->
            return@use File(context.cacheDir, fileName)
                .also { it.createNewFile() }
                .apply { outputStream().use { inputStream?.copyTo(it) } }
        }
}

fun ContentResolver.getFileName(fileUri: Uri): String {
    var name = ""
    val returnCursor = this.query(fileUri, null, null, null, null)
    if (returnCursor != null) {
        val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        returnCursor.moveToFirst()
        name = returnCursor.getString(nameIndex)
        returnCursor.close()
    }
    return name
}

@SuppressLint("SimpleDateFormat")
fun Context.createImageFile(): File {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    return File.createTempFile(
        imageFileName, /* prefix */
        ".jpg", /* suffix */
        cacheDir, /* directory */
    )
}

fun String.convertValidIdentificationFormat(identificationType: String): String {
    return if (identificationType == "NRC") {
        val startIndex = this.indexOf("(")
        val endIndex = this.indexOf(")")
        val nrc = substring(0, startIndex - 1) + substring(endIndex + 1, length)
        nrc
            .replace(" ", "")
            .replace("[", "(")
            .replace("]", ")")
    } else this
}

/*
* enhance logic for mobile number to remove zero
* Eg. 0925487272 -> 925487272
* Eg. 95925487272 -> 925487272
* */
fun String.convertValidPhoneFormat2(): String {
    val prefix = when {
        this.substring(0, 2) == "95" -> this.substring(0, 2).replace("95", "")
        this.substring(0, 2) == "09" -> this.substring(0, 2).replace("09", "9")
        else -> this.substring(0, 2)
    }

    val number = this.substring(2, this.length)
    return (prefix + number)
}

fun String.convertValidPhoneFormat(): String {
    val prefix = if (this.substring(0, 2) == "09") {
        this.substring(0, 2).replace("09", "").plus("959")
    } else {
        this.substring(0, 2)
    }
    val number = this.substring(2, this.length)
    return (prefix + number)
}

fun String.convertDisplayPhoneFormat(): String {
    if (this == "-" || this.isBlank()) return this

    val prefix = when {
        this.substring(0, 2) == "09" -> this.substring(0, 2).replace("09", "").plus("+959")
        this.contains("+") -> this.substring(0, 2)
        else -> "+" + this.substring(0, 2)
    }

    val number = this.substring(2, this.length)
    return (prefix + number)
}

fun Context.findFragmentActivity(): FragmentActivity {
    var context = this
    while (context is ContextWrapper) {
        if (context is FragmentActivity) return context
        context = context.baseContext
    }
    throw IllegalStateException("Permissions should be called in the context of an Activity")
}

fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("no activity")
}

fun String?.orDefault(value: String = "-"): String {
    return if (this.isNullOrBlank()) value else this
}

fun Int?.orDefault(): Int {
    return this ?: 0
}

fun Double?.orDefault(): Double {
    return this ?: 0.0
}


fun Boolean?.orDefault(): Boolean {
    return this ?: false
}

fun Context.goToSetting() {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    val uri = Uri.fromParts("package", this.packageName, null)
    intent.data = uri
    startActivity(intent)
}

fun File.convertToBitmap(context: Context): Bitmap? {
    return try {
        val imgUri: Uri = this.toUri()
        BitmapFactory.decodeStream(context.contentResolver.openInputStream(imgUri))
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
        null
    }
}

fun File.isValidFileSize(): Boolean {
    val fileSizeInBytes = this.length()
    val fileSizeInKB = fileSizeInBytes / 1024
    val fileSizeInMB = fileSizeInKB / 1024
    return fileSizeInMB <= 100
}

private val imageFileExtensions = arrayOf(
    "jpg",
    "png",
    "gif",
    "jpeg"
)

fun isImageFile(file: File): Boolean {
    for (extension in imageFileExtensions) {
        if (file.name.lowercase(Locale.getDefault()).endsWith(extension)) {
            return true
        }
    }
    return false
}

fun String.toNumberFormat(): Int {
    return this.ifBlank { "1.0.0" }
        .replace(".", "")
        .replace("-staging", "")
        .replace("-uat", "")
        .toInt()
}

fun Boolean?.orFalse() = this ?: false
fun Boolean?.orTrue() = this ?: true

fun String.hashWithHMAC(key: String): String {
    try {
        val hmacKey = SecretKeySpec(key.toByteArray(), "HmacSHA256")
        val hmac = Mac.getInstance("HmacSHA256")
        hmac.init(hmacKey)
        val hash = hmac.doFinal(this.toByteArray())
        return Base64.encodeToString(hash, Base64.DEFAULT).trim()
    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
    } catch (e: InvalidKeyException) {
        e.printStackTrace()
    }
    return ""
}

fun String.sha256(): String {
    var md: MessageDigest? = null
    try {
        md = MessageDigest.getInstance("SHA-256")
    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
    }
    md!!.update(this.toByteArray())
    val byteData = md.digest()
    val sb = StringBuffer()
    for (i in byteData.indices) {
        sb.append(((byteData[i].toInt() and 0xff) + 0x100).toString(16).substring(1))
    }
    return sb.toString()
}

fun String.md5(): String {
    return this.hash("MD5")
}

private fun String.hash(algorithm: String): String {
    return try {
        val bytes = this.toByteArray()
        val md = MessageDigest.getInstance(algorithm)
        val digest = md.digest(bytes)
        digest.fold("") { str, it -> str + "%02x".format(it) }
    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
        ""
    }
}

fun String.toEncodedUrl(): String {
    return URLEncoder.encode(this, StandardCharsets.UTF_8.toString())
}

fun Context.openSubscriptionManagement() {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse("https://play.google.com/store/account/subscriptions")
    this.startActivity(intent)
}

fun String.toBase64Encoded(): String {
    return java.util.Base64.getEncoder()
        .encodeToString(this.toByteArray(Charsets.UTF_8))
}

fun Double.toPricingFormat(locale: Locale = Locale.getDefault()): String {
    // Get the number format for the locale
    val numberFormat = NumberFormat.getInstance(locale)

    // Format the value using the locale's number format
    val formattedValue = numberFormat.format(this)

    // Get decimal and grouping separators for the locale
    val decimalFormatSymbols = DecimalFormatSymbols.getInstance(locale)
    val decimalSeparator = decimalFormatSymbols.decimalSeparator
    val groupingSeparator = decimalFormatSymbols.groupingSeparator

    return removeTrailingZeros(formattedValue, decimalSeparator, groupingSeparator)
}

fun Int.toPricingFormat(locale: Locale = Locale.getDefault()): String {
    // Get the number format for the locale
    val numberFormat = NumberFormat.getInstance(locale)

    // Format the value using the locale's number format
    val formattedValue = numberFormat.format(this)

    // Get decimal and grouping separators for the locale
    val decimalFormatSymbols = DecimalFormatSymbols.getInstance(locale)
    val decimalSeparator = decimalFormatSymbols.decimalSeparator
    val groupingSeparator = decimalFormatSymbols.groupingSeparator

    return removeTrailingZeros(formattedValue, decimalSeparator, groupingSeparator)
}

fun removeTrailingZeros(value: String, decimalSeparator: Char, groupingSeparator: Char): String {
    // Create a regex pattern to match trailing zeros after the decimal separator
    val regex = """[${decimalSeparator}]0+$""".toRegex()

    // Remove the matched trailing zeros
    return value.replace(regex, "")
}