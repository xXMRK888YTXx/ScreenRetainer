package com.xxmrk888ytxx.screenretainer.DI.modules

import com.xxmrk888ytxx.screenretainer.UseCases.LockDeviceUseCase.LockDeviceUseCase
import com.xxmrk888ytxx.screenretainer.UseCases.LockDeviceUseCase.LockDeviceUseCaseImpl
import com.xxmrk888ytxx.screenretainer.UseCases.RemoveAppUseCase.RemoveAppUseCase
import com.xxmrk888ytxx.screenretainer.UseCases.RemoveAppUseCase.RemoveAppUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface UseCasesModule {
    @Binds
    fun bindLockDeviceUseCase(lockDeviceUseCase:LockDeviceUseCaseImpl) : LockDeviceUseCase

    @Binds
    fun bindRemoveAppUseCase(RemoveAppUseCase:RemoveAppUseCaseImpl) : RemoveAppUseCase
}