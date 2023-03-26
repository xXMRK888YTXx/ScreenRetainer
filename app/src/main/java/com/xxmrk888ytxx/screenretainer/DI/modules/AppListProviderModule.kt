package com.xxmrk888ytxx.screenretainer.DI.modules

import com.xxmrk888ytxx.applistscreen.contract.AppLaunchManager
import com.xxmrk888ytxx.applistscreen.contract.AppListProvider
import com.xxmrk888ytxx.screenretainer.glue.AppListScreen.AppLaunchManagerImpl
import com.xxmrk888ytxx.screenretainer.glue.AppListScreen.AppListProviderImpl
import dagger.Binds
import dagger.Module

@Module
interface AppListProviderModule {
    @Binds
    fun bindAppListProvider(
        appListProvider: AppListProviderImpl
    ) : AppListProvider

    @Binds
    fun bindAppLaunchManager(
        appLaunchManagerImpl: AppLaunchManagerImpl
    ) : AppLaunchManager
}