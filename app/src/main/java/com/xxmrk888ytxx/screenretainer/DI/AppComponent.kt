package com.xxmrk888ytxx.screenretainer.DI

import android.content.Context
import com.xxmrk888ytxx.eventdevicetracker.OpenAppChangedCallback
import com.xxmrk888ytxx.quicksettingsservice.LockCurrentAppButtonClickedCallback
import com.xxmrk888ytxx.screenretainer.DI.modules.*
import com.xxmrk888ytxx.screenretainer.DI.modules.ShareModule
import com.xxmrk888ytxx.screenretainer.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        AppListProviderModule::class,
        OpenAppChangedTrackerServiceModule::class,
        PreferencesStorageModule::class,
        ShareModule::class,
        UseCasesModule::class,
        FixationManagerModule::class,
        SettingsScreenModule::class,
        FavoriteAppRepositoryModule::class,
        LockCurrentAppButtonClickedCallbackModule::class,
        PermissionManagerModule::class
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

    val lockCurrentAppButtonClickedCallback: LockCurrentAppButtonClickedCallback
}