package com.xxmrk888ytxx.screenretainer.DI.modules

import com.xxmrk888ytxx.admobmanager.AdMobManager
import com.xxmrk888ytxx.screenretainer.domain.AdManager.AdManager
import com.xxmrk888ytxx.screenretainer.domain.AdManager.AdManagerImpl
import dagger.Binds
import dagger.Module

@Module
interface AdManagerModule {
    @Binds
    fun bindAdManager(
        adManager: AdManagerImpl
    ) : AdManager
}