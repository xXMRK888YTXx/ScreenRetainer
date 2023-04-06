package com.xxmrk888ytxx.screenretainer.DI.modules

import android.content.Context
import android.os.Build
import com.xxmrk888ytxx.quicksettingsservice.ButtonActiveStateController
import com.xxmrk888ytxx.quicksettingsservice.LockCurrentAppQuickButtonService
import com.xxmrk888ytxx.screenretainer.DI.AppScope
import dagger.Module
import dagger.Provides

@Module
class ButtonActiveStateControllerModule {

    @Provides
    @AppScope
    fun provideButtonActiveStateController(context: Context) : ButtonActiveStateController {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LockCurrentAppQuickButtonService.Companion.ButtonActiveStateControllerFactory().create(context)
        } else {
            return object : ButtonActiveStateController {
                override suspend fun enableButton() {}

                override suspend fun disableButton() {}

            }
        }
    }
}