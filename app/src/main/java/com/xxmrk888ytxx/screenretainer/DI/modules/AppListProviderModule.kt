package com.xxmrk888ytxx.screenretainer.DI.modules

import com.xxmrk888ytxx.applistscreen.contract.*
import com.xxmrk888ytxx.screenretainer.glue.AppListScreen.*
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
        appLaunchManagerImpl: AppLaunchAndActivateScreenFixationContractImpl
    ) : AppLaunchAndActivateScreenFixationContract

    @Binds
    fun bindRequestPermissionContract(
        RequestPermissionContract: RequestPermissionContractImpl
    ) : RequestPermissionContract

    @Binds
    fun bindCheckPermissionContract(
        CheckPermissionContract: CheckPermissionContractImpl
    ) :CheckPermissionContract

    @Binds
    fun bindManageFavoriteAppContract(
        ManageFavoriteAppContractImpl: ManageFavoriteAppContractImpl
    ) : ManageFavoriteAppContract
}