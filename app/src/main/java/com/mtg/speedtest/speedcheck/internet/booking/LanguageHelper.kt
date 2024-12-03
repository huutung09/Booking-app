package com.mtg.speedtest.speedcheck.internet.booking

import android.content.Context
import android.content.res.Configuration
import java.util.*

object LanguageHelper {
    private const val LANG_PREF_KEY = "lang_pref"

    fun setAppLanguage(context: Context, languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val resources = context.resources
        val configuration = Configuration(resources.configuration)
        configuration.setLocale(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)

        // Save the selected language for future use
        val sharedPreferences = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(LANG_PREF_KEY, languageCode).apply()
    }

    private fun getAppLanguage(context: Context): String {
        val sharedPreferences = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString(LANG_PREF_KEY, "en") ?: "en"
    }

    fun updateLanguage(context: Context) {
        val selectedLanguage = getAppLanguage(context)
        setAppLanguage(context, selectedLanguage)
    }
}