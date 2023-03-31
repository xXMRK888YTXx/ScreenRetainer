package com.xxmrk888ytxx.screenretainer.DI.modules

import com.xxmrk888ytxx.screenretainer.domain.AgreeDialogManager.AgreeDialogManager
import com.xxmrk888ytxx.screenretainer.domain.AgreeDialogManager.AgreeDialogManagerImpl
import dagger.Binds
import dagger.Module

@Module
interface AgreeDialogManagerModule {
    @Binds
    fun bindAgreeDialogManager(
        AgreeDialogManagerImpl: AgreeDialogManagerImpl
    ) : AgreeDialogManager
}