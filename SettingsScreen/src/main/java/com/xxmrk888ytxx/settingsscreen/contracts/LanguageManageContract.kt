package com.xxmrk888ytxx.settingsscreen.contracts

import com.xxmrk888ytxx.settingsscreen.models.AppLanguage

interface LanguageManageContract {

    val supportedLanguage:List<AppLanguage>

    fun setupLanguage(appLanguage: AppLanguage)

    val currentLanguage: AppLanguage
}