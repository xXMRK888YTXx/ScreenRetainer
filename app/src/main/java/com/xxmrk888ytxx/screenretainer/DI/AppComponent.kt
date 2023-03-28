package com.xxmrk888ytxx.screenretainer.DI

import android.content.Context
import com.xxmrk888ytxx.eventdevicetracker.OpenAppChangedCallback
import com.xxmrk888ytxx.screenretainer.DI.modules.AppListProviderModule
import com.xxmrk888ytxx.screenretainer.DI.modules.OpenAppChangedTrackerServiceModule
import com.xxmrk888ytxx.screenretainer.DI.modules.PreferencesStorageModule
import com.xxmrk888ytxx.screenretainer.DI.modules.ShareModule
import com.xxmrk888ytxx.screenretainer.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        AppListProviderModule::class,
        OpenAppChangedTrackerServiceModule::class,
        PreferencesStorageModule::class,
        ShareModule::class
    ]
)
@AppScope
interface AppComponent {
    fun inject(mainActivity: MainActivity)

    @dagger.Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context) : AppComponent
    }

    val openAppChangedCallback: OpenAppChangedCallback
}