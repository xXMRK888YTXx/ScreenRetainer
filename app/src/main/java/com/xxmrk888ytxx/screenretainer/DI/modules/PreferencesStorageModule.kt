package com.xxmrk888ytxx.screenretainer.DI.modules

import android.content.Context
import com.xxmrk888ytxx.coreandroid.DepsProvider.Const.preferencesFileName
import com.xxmrk888ytxx.preferencesstorage.PreferencesStorage
import dagger.Module
import dagger.Provides

@Module
class PreferencesStorageModule {
    @Provides
    fun providePreferencesStorage(context: Context) : PreferencesStorage {
        return PreferencesStorage.Builder(preferencesFileName,context).build()
    }
}