package com.xxmrk888ytxx.settingsscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xxmrk888ytxx.settingsscreen.contracts.LanguageManageContract
import com.xxmrk888ytxx.settingsscreen.contracts.ProvideAppInfoContract
import com.xxmrk888ytxx.settingsscreen.models.AppLanguage
import com.xxmrk888ytxx.settingsscreen.models.DialogState.DialogState
import com.xxmrk888ytxx.settingsscreen.models.DialogState.LanguageDialogState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val provideAppInfoContract: ProvideAppInfoContract,
    private val languageManageContract: LanguageManageContract
) : ViewModel() {

    internal val appVersion = provideAppInfoContract.appVersion

    internal val supportedLanguage = languageManageContract.supportedLanguage

    private val _dialogState = MutableStateFlow(DialogState())

    internal val dialogState = _dialogState.asStateFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), DialogState())

    internal fun changeCurrentLanguage() {
        val currentSelectedLanguage = dialogState.value.languageDialogState
                as? LanguageDialogState.Showed ?: return
        hideLanguageDialog()

        languageManageContract.setupLanguage(currentSelectedLanguage.currentSelectedLanguage)
    }

    fun showLanguageDialog() {
        viewModelScope.launch {
            val currentLanguage = languageManageContract.currentLanguage

            _dialogState.emit(
                _dialogState.value.copy(
                    languageDialogState = LanguageDialogState.Showed(currentLanguage)
                )
            )
        }
    }

    fun hideLanguageDialog() {
        viewModelScope.launch {
            _dialogState.emit(
                _dialogState.value.copy(
                    languageDialogState = LanguageDialogState.Hidden
                )
            )
        }
    }

    fun changeCurrentSelectedLanguage(language: AppLanguage) {
        if(dialogState.value.languageDialogState !is LanguageDialogState.Showed) return

        viewModelScope.launch { _dialogState.emit(
            dialogState.value.copy(
                languageDialogState = LanguageDialogState.Showed(language)
            )
        ) }
    }
}