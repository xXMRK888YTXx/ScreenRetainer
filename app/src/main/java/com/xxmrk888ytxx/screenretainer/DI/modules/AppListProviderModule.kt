package com.xxmrk888ytxx.screenretainer.DI.modules

import com.xxmrk888ytxx.applistscreen.contract.AppLaunchContract
import com.xxmrk888ytxx.applistscreen.contract.AppListProvideContract
import com.xxmrk888ytxx.applistscreen.contract.CheckPermissionContract
import com.xxmrk888ytxx.applistscreen.contract.RequestPermissionContract
import com.xxmrk888ytxx.screenretainer.glue.AppListScreen.AppLaunchContractImpl
import com.xxmrk888ytxx.screenretainer.glue.AppListScreen.AppListProvideContractImpl
import com.xxmrk888ytxx.screenretainer.glue.AppListScreen.CheckPermissionContractImpl
import com.xxmrk888ytxx.screenretainer.glue.AppListScreen.RequestPermissionContractImpl
import dagger.Binds
import dagger.Module

@Module
interface AppListProviderModule {
    @Binds
    fun bindAppListProvider(
        appListProvider: AppListProvideContractImpl
    ) : AppListProvideContract

    @Binds
    fun bindAppLaunchManager(
        appLaunchManagerImpl: AppLaunchContractImpl
    ) : AppLaunchContract

    @Binds
    fun bindRequestPermissionContract(
        RequestPermissionContract: RequestPermissionContractImpl
    ) : RequestPermissionContract

    @Binds
    fun bindCheckPermissionContract(
        CheckPermissionContract: CheckPermissionContractImpl
    ) :CheckPermissionContract
}