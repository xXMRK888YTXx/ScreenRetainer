package com.xxmrk888ytxx.screenretainer.DI.modules

import com.xxmrk888ytxx.screenretainer.glue.SettingsScreen.LanguageManageContractImpl
import com.xxmrk888ytxx.screenretainer.glue.SettingsScreen.ProvideAppInfoContractImpl
import com.xxmrk888ytxx.settingsscreen.contracts.LanguageManageContract
import com.xxmrk888ytxx.settingsscreen.contracts.ProvideAppInfoContract
import dagger.Binds
import dagger.Module

@Module
interface SettingsScreenModule {
    @Binds
    fun bindProvideAppInfoContract(
        provideAppInfoContract: ProvideAppInfoContractImpl
    ): ProvideAppInfoContract

    @Binds
    fun bindLanguageManageContract(
        LanguageManageContract:LanguageManageContractImpl
    ) : LanguageManageContract
}