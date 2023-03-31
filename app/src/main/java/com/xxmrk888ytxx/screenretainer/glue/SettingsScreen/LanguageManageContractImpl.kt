package com.xxmrk888ytxx.screenretainer.glue.SettingsScreen

import android.annotation.SuppressLint
import android.content.Context
import com.xxmrk888ytxx.screenretainer.R
import com.xxmrk888ytxx.screenretainer.domain.LocalizationManager.LocalizationManager
import com.xxmrk888ytxx.screenretainer.domain.LocalizationManager.SupportedLanguage
import com.xxmrk888ytxx.settingsscreen.contracts.LanguageManageContract
import com.xxmrk888ytxx.settingsscreen.models.AppLanguage
import javax.inject.Inject

class LanguageManageContractImpl @Inject constructor(
    private val localizationManager: LocalizationManager,
    private val context:Context
) : LanguageManageContract {

    override val supportedLanguage: List<AppLanguage>
        @SuppressLint("ResourceType")
        get() = SupportedLanguage.localeList.map {
            AppLanguage(
                name = context.getString(it.title),
                code = it.code
            )
        }

    override fun setupLanguage(appLanguage: AppLanguage) {
        val supportedLanguage = SupportedLanguage.localeList
            .firstOrNull() { it.code == appLanguage.code } ?: return

        localizationManager.setupLocalization(supportedLanguage)
    }

    override val currentLanguage: AppLanguage
        @SuppressLint("ResourceType")
        get() {
            val supportedLanguage = localizationManager.currentLocalization
            return AppLanguage(name = context.getString(supportedLanguage.title),code = supportedLanguage.code)
        }
}