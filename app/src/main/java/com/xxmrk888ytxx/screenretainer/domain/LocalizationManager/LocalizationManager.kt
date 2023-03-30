package com.xxmrk888ytxx.screenretainer.domain.LocalizationManager

interface LocalizationManager {

    fun setupLocalization(supportedLanguage: SupportedLanguage)

    val currentLocalization: SupportedLanguage
}