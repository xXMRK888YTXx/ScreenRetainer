package com.xxmrk888ytxx.settingsscreen

import androidx.lifecycle.ViewModel
import com.xxmrk888ytxx.settingsscreen.contracts.ProvideAppInfoContract
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val provideAppInfoContract: ProvideAppInfoContract
) : ViewModel() {

    internal val appVersion = provideAppInfoContract.appVersion
}