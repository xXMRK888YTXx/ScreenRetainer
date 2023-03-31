package com.xxmrk888ytxx.screenretainer.DI.modules

import com.xxmrk888ytxx.coreandroid.DepsProvider.ToastManager
import com.xxmrk888ytxx.screenretainer.share.ToastManagerImpl
import dagger.Binds
import dagger.Module

@Module
internal interface ShareModule {
    @Binds
    fun bindToastManager(toastManager: ToastManagerImpl) : ToastManager
}