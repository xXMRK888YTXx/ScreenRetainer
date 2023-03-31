package com.xxmrk888ytxx.screenretainer.DI.modules

import com.xxmrk888ytxx.quicksettingsservice.LockCurrentAppButtonClickedCallback
import com.xxmrk888ytxx.screenretainer.domain.LockCurrentAppButtonClickedCallback.LockCurrentAppButtonClickedCallbackImpl
import dagger.Binds
import dagger.Module

@Module
interface LockCurrentAppButtonClickedCallbackModule {

    @Binds
    fun bindLockCurrentAppButtonClickedCallback(
        LockCurrentAppButtonClickedCallback:LockCurrentAppButtonClickedCallbackImpl
    ) : LockCurrentAppButtonClickedCallback
}