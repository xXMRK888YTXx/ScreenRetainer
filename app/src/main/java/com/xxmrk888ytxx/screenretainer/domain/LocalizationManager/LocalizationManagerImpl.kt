package com.xxmrk888ytxx.screenretainer.domain.LocalizationManager

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import java.util.*
import javax.inject.Inject

class LocalizationManagerImpl @Inject constructor(

) : LocalizationManager  {

    override fun setupLocalization(supportedLanguage: SupportedLanguage) {
        AppCompatDelegate.setApplicationLocales(
            LocaleListCompat.create(Locale(supportedLanguage.code))
        )
    }

    override val currentLocalization: SupportedLanguage
        get() {
            return when(AppCompatDelegate.getApplicationLocales()[0]?.language) {
                SupportedLanguage.EN.code -> SupportedLanguage.EN
                SupportedLanguage.RU.code -> SupportedLanguage.RU
                else -> SupportedLanguage.System
            }
        }
}