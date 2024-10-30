package com.dev.akp_base.localization.model

import java.util.Locale


class LocalizationModel(
    var name:String = "akp",
    var error:String = "Error",
    var sessionExpireTitle:String = "Session expired",
    var sessionExpireBody:String = "Your session is expired,Please login again to continue",
    var appNotAvailableInRegion:String = "This app is not available in this region"
)

val ENGLISH = Locale("en")
val MYANMAR = Locale("mm")
