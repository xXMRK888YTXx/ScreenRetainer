package com.xxmrk888ytxx.screenretainer.DI.modules

import com.xxmrk888ytxx.screenretainer.domain.QuickSettingsButtonSwitcher.QuickSettingsButtonSwitcher
import com.xxmrk888ytxx.screenretainer.domain.QuickSettingsButtonSwitcher.QuickSettingsButtonSwitcherImpl
import dagger.Binds
import dagger.Module

@Module
interface QuickSettingsButtonSwitcherModule {
    @Binds
    fun bindQuickSettingsButtonSwitcher(quickSettingsButtonSwitcher: QuickSettingsButtonSwitcherImpl) : QuickSettingsButtonSwitcher
}