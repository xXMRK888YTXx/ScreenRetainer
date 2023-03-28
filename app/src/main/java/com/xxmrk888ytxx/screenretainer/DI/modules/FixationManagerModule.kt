package com.xxmrk888ytxx.screenretainer.DI.modules

import com.xxmrk888ytxx.screenretainer.domain.FixationManager.FixationManager
import com.xxmrk888ytxx.screenretainer.domain.FixationManager.FixationManagerImpl
import dagger.Binds
import dagger.Module

@Module
interface FixationManagerModule {
    @Binds
    fun bindFixationManager(fixationManagerImpl: FixationManagerImpl) : FixationManager
}