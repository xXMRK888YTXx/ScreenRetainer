package com.xxmrk888ytxx.screenretainer.DI.modules

import com.xxmrk888ytxx.screenretainer.domain.LocalizationManager.LocalizationManager
import com.xxmrk888ytxx.screenretainer.domain.LocalizationManager.LocalizationManagerImpl
import dagger.Binds
import dagger.Module

@Module
interface LocalizationManagerModule {
    @Binds
    fun bindLocalizationManager(
        localizationManager: LocalizationManagerImpl
    ) : LocalizationManager
}