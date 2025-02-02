package com.mtg.speedtest.speedcheck.internet.booking

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object CommonUtils {
    const val nameEmail: String = "verifiedapp.help@gmail.com"
    const val titleEmail: String = "User feedback Speed Test"
    const val linkShare: String = "Let me recommend you this application: https://play.google.com/store/apps/details?id="
    const val linkPrivacy: String =
        "https://firebasestorage.googleapis.com/v0/b/all-documents-55e23.appspot.com/o/policy%20Wifi%20Analyzer.html?alt=media&token=56df7e70-9611-4908-8b90-84f4e0783cdb&_gl=1*1ok9d6f*_ga*MTMwNDU3NjU2LjE2NzcxNTEzMTM.*_ga_CW55HF8NVT*MTY5NjIxMzM2OS43Ni4xLjE2OTYyMTM0MjcuMi4wLjA"
    const val keyBackMainScreenByLanguageScreen: String = "keyBackMainScreenByLanguageScreen"
    const val keyIndexAccount: String = "keyIndexAccount"
    const val nameDatabase: String = "RoomDatabase"
    const val BASE_URL = "http://192.168.1.135:3000/api/"
    const val REQUEST_TIMEOUT_DURATION = 60


    fun formatVndMoney(money: String) : String {
        val amount = money.toLong()
        return String.format("%,d VND", amount)
    }

    fun formatDate(dateString: String?) : String {
        if (dateString == null) return ""
        val localDateTime = Instant.parse(dateString)
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        return localDateTime.format(formatter)
    }
}