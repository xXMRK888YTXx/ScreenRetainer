package com.xxmrk888ytxx.screenretainer.DI.modules

import com.xxmrk888ytxx.screenretainer.UseCases.LockDeviceUseCase.LockDeviceUseCase
import com.xxmrk888ytxx.screenretainer.UseCases.LockDeviceUseCase.LockDeviceUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface UseCasesModule {
    @Binds
    fun bindLockDeviceUseCase(lockDeviceUseCase:LockDeviceUseCaseImpl) : LockDeviceUseCase
}