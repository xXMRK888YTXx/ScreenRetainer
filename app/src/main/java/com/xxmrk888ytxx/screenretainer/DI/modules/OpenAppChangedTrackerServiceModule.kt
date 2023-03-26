package com.xxmrk888ytxx.screenretainer.DI.modules

import com.xxmrk888ytxx.eventdevicetracker.OpenAppChangedCallback
import com.xxmrk888ytxx.screenretainer.DI.AppScope
import com.xxmrk888ytxx.screenretainer.domain.OpenAppChangedCallbackImpl
import dagger.Binds
import dagger.Module

@Module
interface OpenAppChangedTrackerServiceModule {
    @Binds
    fun bindOpenAppChangedCallbackImpl(
        EventDeviceTrackerService: OpenAppChangedCallbackImpl
    ) : OpenAppChangedCallback
}