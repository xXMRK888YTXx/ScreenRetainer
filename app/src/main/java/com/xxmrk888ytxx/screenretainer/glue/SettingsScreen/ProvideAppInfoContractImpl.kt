package com.xxmrk888ytxx.screenretainer.glue.SettingsScreen

import com.xxmrk888ytxx.screenretainer.BuildConfig
import com.xxmrk888ytxx.settingsscreen.contracts.ProvideAppInfoContract
import javax.inject.Inject

class ProvideAppInfoContractImpl @Inject constructor() : ProvideAppInfoContract {
    override val appVersion: String
        get() = BuildConfig.VERSION_NAME
}