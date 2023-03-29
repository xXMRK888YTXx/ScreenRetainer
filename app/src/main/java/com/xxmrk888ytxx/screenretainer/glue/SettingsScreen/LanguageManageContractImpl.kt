package com.xxmrk888ytxx.screenretainer.glue.SettingsScreen

import com.xxmrk888ytxx.settingsscreen.contracts.LanguageManageContract
import com.xxmrk888ytxx.settingsscreen.models.AppLanguage
import javax.inject.Inject

class LanguageManageContractImpl @Inject constructor() : LanguageManageContract {

    override val supportedLanguage: List<AppLanguage>
        get() = listOf(
            AppLanguage(
                "Русский",
                "TODO"
            ),

            AppLanguage(
                "English",
                "TODO"
            ),
        )

    override fun setupLanguage(appLanguage: AppLanguage) {
        println(appLanguage)
    }

    override val currentLanguage: AppLanguage
        get() = supportedLanguage[0]
}