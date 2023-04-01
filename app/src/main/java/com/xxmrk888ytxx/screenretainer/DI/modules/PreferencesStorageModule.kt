package com.xxmrk888ytxx.screenretainer.DI.modules

import android.content.Context
import com.xxmrk888ytxx.coreandroid.DepsProvider.Const.preferencesFileName
import com.xxmrk888ytxx.preferencesstorage.PreferencesStorage
import com.xxmrk888ytxx.screenretainer.DI.AppScope
import dagger.Module
import dagger.Provides

@Module
class PreferencesStorageModule {
    @Provides
    @AppScope
    fun providePreferencesStorage(context: Context) : PreferencesStorage {
        return PreferencesStorage.Factory().create(preferencesFileName,context)
    }
}