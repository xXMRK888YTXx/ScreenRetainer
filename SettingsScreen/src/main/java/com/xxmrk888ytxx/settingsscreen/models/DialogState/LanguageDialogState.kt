package com.xxmrk888ytxx.settingsscreen.models.DialogState

import com.xxmrk888ytxx.settingsscreen.models.AppLanguage

sealed class LanguageDialogState {
    object Hidden : LanguageDialogState()

    data class Showed (
        val currentSelectedLanguage: AppLanguage
    ) : LanguageDialogState()
}
