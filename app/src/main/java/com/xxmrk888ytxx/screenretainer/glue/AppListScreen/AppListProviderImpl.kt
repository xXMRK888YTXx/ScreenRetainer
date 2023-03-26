package com.xxmrk888ytxx.screenretainer.glue.AppListScreen

import androidx.core.graphics.drawable.toBitmap
import com.xxmrk888ytxx.applistscreen.contract.AppListProvider
import com.xxmrk888ytxx.applistscreen.models.AppInfoModel
import com.xxmrk888ytxx.packageinfoprovider.PackageInfoProvider
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppListProviderImpl @Inject constructor(
    private val packageInfoProvider: PackageInfoProvider
) : AppListProvider {
    override suspend fun provide(): List<AppInfoModel> = packageInfoProvider.getAllApplicationInfo().map {
        AppInfoModel(
            appName = it.appName,
            appPackageName = it.packageName,
            appIcon = it.icon?.toBitmap()
        )
    }
}