package com.xxmrk888ytxx.screenretainer.glue.AppListScreen

import androidx.core.graphics.drawable.toBitmap
import com.xxmrk888ytxx.applistscreen.contract.AppListProvideContract
import com.xxmrk888ytxx.applistscreen.models.AppInfoModel
import com.xxmrk888ytxx.packageinfoprovider.PackageInfoProvider
import javax.inject.Inject

class AppListProvideContractImpl @Inject constructor(
    private val packageInfoProvider: PackageInfoProvider
) : AppListProvideContract {
    override suspend fun provide(): List<AppInfoModel> = packageInfoProvider.getAllApplicationInfoOnlyWithLaunchActivity().map {
        AppInfoModel(
            appName = it.appName,
            appPackageName = it.packageName,
            appIcon = it.icon?.toBitmap()
        )
    }
}