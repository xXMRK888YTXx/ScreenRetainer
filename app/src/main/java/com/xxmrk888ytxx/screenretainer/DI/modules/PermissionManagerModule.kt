package com.xxmrk888ytxx.screenretainer.DI.modules

import com.xxmrk888ytxx.screenretainer.domain.PermissionManager.PermissionManager
import com.xxmrk888ytxx.screenretainer.domain.PermissionManager.PermissionManagerImpl
import dagger.Binds
import dagger.Module

@Module
interface PermissionManagerModule {
    @Binds
    fun bindPermissionManager(
        PermissionManagerImpl: PermissionManagerImpl
    ) : PermissionManager
}